package com.tomcandev.mobiledatausage.presentation.yearlydatausage

import android.annotation.SuppressLint
import androidx.paging.PageKeyedDataSource
import com.tomcandev.mobiledatausage.domain.constants.CONSTANTS.PAGE_SIZE
import com.tomcandev.mobiledatausage.domain.repository.MobileDataUsageRepository
import com.tomcandev.mobiledatausage.presentation.yearlydatausage.model.YearlyItemModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class YearlyRemotePagedSource(private val mobileDataUsageRepository: MobileDataUsageRepository) :
    PageKeyedDataSource<Long, YearlyListViewType>() {

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, YearlyListViewType>
    ) {
        mobileDataUsageRepository.getMobileDataUsageList(0, PAGE_SIZE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                callback.onResult(response.groupBy { it.year }
                    .map { yearMap ->
                        YearlyListViewType.YearlyItem(
                            YearlyItemModel(
                                yearMap.value.sumByDouble { it.volume },
                                yearMap.key
                            )
                        )
                    }, null, 1L)
            }, { e ->

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
                callback.onResult(response.groupBy { it.year }
                    .map { yearMap ->
                        YearlyListViewType.YearlyItem(
                            YearlyItemModel(
                                yearMap.value.sumByDouble { it.volume },
                                yearMap.key
                            )
                        )
                    }, nextKey)
            }, { e ->

            })
    }

    override fun loadBefore(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, YearlyListViewType>
    ) {

    }
}