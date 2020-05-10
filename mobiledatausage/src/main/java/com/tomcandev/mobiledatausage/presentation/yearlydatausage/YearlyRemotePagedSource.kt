package com.tomcandev.mobiledatausage.presentation.yearlydatausage

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.tomcandev.mobiledatausage.domain.constants.CONSTANTS.PAGE_SIZE
import com.tomcandev.mobiledatausage.domain.repository.MobileDataUsageRepository
import com.tomcandev.mobiledatausage.presentation.utils.NetworkState
import com.tomcandev.mobiledatausage.presentation.utils.Utils
import com.tomcandev.mobiledatausage.presentation.yearlydatausage.model.QuarterItemModel
import com.tomcandev.mobiledatausage.presentation.yearlydatausage.model.YearlyItemModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class YearlyRemotePagedSource(private val mobileDataUsageRepository: MobileDataUsageRepository) :
    PageKeyedDataSource<Long, YearlyListViewType>() {

    private val yearlyList = ArrayList<YearlyListViewType>()
    val initialLoading = MutableLiveData<NetworkState>()

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, YearlyListViewType>
    ) {
        initialLoading.postValue(NetworkState.Loading)
        mobileDataUsageRepository.getMobileDataUsageList(0, PAGE_SIZE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val newList = response.groupBy { it.year }
                    .map { yearMap ->
                        YearlyListViewType.YearlyItem(
                            YearlyItemModel(
                                yearMap.value.sumByDouble { it.volume },
                                yearMap.key,
                                yearMap.value.map { QuarterItemModel(it.volume, it.quarter) },
                                Utils.isDownAnyQuarterByDomainModel(yearMap.value)
                            )
                        )
                    }
                yearlyList.addAll(newList)
                initialLoading.postValue(NetworkState.Loaded)
                callback.onResult(newList, null, 1L)
            }, { e ->
                initialLoading.postValue(NetworkState.Error(e.message ?: ""))
            })
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, YearlyListViewType>
    ) {
        mobileDataUsageRepository.getMobileDataUsageList(
            params.key.toInt() * params.requestedLoadSize,
            params.requestedLoadSize
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val nextKey = if (response.isEmpty()) null else params.key + 1

                val newList = ArrayList<YearlyListViewType>(response.groupBy { it.year }
                    .map { yearMap ->
                        YearlyListViewType.YearlyItem(
                            YearlyItemModel(
                                yearMap.value.sumByDouble { it.volume },
                                yearMap.key,
                                yearMap.value.map { QuarterItemModel(it.volume, it.quarter) },
                                Utils.isDownAnyQuarterByDomainModel(yearMap.value)
                            )
                        )
                    })

                val last = yearlyList.lastOrNull()
                val first = newList.firstOrNull()
                if (last is YearlyListViewType.YearlyItem
                    && first is YearlyListViewType.YearlyItem
                    && last.yearlyItemModel.year == first.yearlyItemModel.year
                ) {
                    newList.remove(first)
                    val year = last.yearlyItemModel.year
                    val volume = last.yearlyItemModel.volume + first.yearlyItemModel.volume
                    val quarters = ArrayList<QuarterItemModel>()
                    quarters.addAll(last.yearlyItemModel.quarters)
                    quarters.addAll(first.yearlyItemModel.quarters)
                    val isAnyDownVolume = Utils.isDownAnyQuarterByItemModel(quarters)
                    last.yearlyItemModel = YearlyItemModel(
                        volume = volume,
                        quarters = quarters,
                        year = year,
                        isAnyDownVolume = isAnyDownVolume
                    )
                }

                yearlyList.addAll(newList)
                callback.onResult(newList as List<YearlyListViewType>, nextKey)

            }, { e ->

            })
    }

    override fun loadBefore(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, YearlyListViewType>
    ) {

    }
}