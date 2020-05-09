package com.tomcandev.mobiledatausage.presentation.yearlydatausage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList

class YearlyListViewModelFactory(
    private val yearlyRemotePagedSource: YearlyRemotePagedSource,
    private val pagedListConfig: PagedList.Config
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return YearlyListViewModel(
            yearlyRemotePagedSource,
            pagedListConfig
        ) as T
    }
}