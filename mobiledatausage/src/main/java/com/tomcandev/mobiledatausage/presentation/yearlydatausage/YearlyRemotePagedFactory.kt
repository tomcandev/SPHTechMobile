package com.tomcandev.mobiledatausage.presentation.yearlydatausage

import androidx.paging.DataSource

class YearlyRemotePagedFactory(private val yearlyRemotePagedSource: YearlyRemotePagedSource): DataSource.Factory<Long, YearlyListViewType>() {
    override fun create(): DataSource<Long, YearlyListViewType> = yearlyRemotePagedSource
}