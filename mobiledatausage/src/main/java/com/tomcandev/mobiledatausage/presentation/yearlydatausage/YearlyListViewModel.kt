package com.tomcandev.mobiledatausage.presentation.yearlydatausage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class YearlyListViewModel(
    yearlyRemotePagedSource: YearlyRemotePagedSource,
    pagedListConfig: PagedList.Config
) : ViewModel() {
    val pagedList: LiveData<PagedList<YearlyListViewType>> = LivePagedListBuilder(YearlyRemotePagedFactory(yearlyRemotePagedSource), pagedListConfig).build()
    fun refresh() {
        pagedList.value?.dataSource?.invalidate()
    }
}