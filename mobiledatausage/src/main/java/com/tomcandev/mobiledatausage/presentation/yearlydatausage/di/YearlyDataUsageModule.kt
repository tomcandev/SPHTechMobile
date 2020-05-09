package com.tomcandev.mobiledatausage.presentation.yearlydatausage.di

import androidx.paging.PagedList
import com.tomcandev.core.base.di.scope.FragmentScope
import com.tomcandev.mobiledatausage.domain.constants.CONSTANTS.PAGE_SIZE
import com.tomcandev.mobiledatausage.domain.repository.MobileDataUsageRepository
import com.tomcandev.mobiledatausage.presentation.yearlydatausage.YearlyListAdapter
import com.tomcandev.mobiledatausage.presentation.yearlydatausage.YearlyListViewModelFactory
import com.tomcandev.mobiledatausage.presentation.yearlydatausage.YearlyRemotePagedSource
import dagger.Module
import dagger.Provides

@Module
class YearlyDataUsageModule {
    @Provides
    @FragmentScope
    fun provideYearlyListViewModelFactory(
        yearlyRemotePagedSource: YearlyRemotePagedSource,
        pagedListConfig: PagedList.Config
    ): YearlyListViewModelFactory =
        YearlyListViewModelFactory(yearlyRemotePagedSource, pagedListConfig)

    @Provides
    @FragmentScope
    fun provideYearlyRemotePagedSource(mobileDataUsageRepository: MobileDataUsageRepository): YearlyRemotePagedSource =
        YearlyRemotePagedSource(mobileDataUsageRepository)

    @Provides
    @FragmentScope
    fun providePagedListConfig(): PagedList.Config =
        PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE).build()

    @Provides
    @FragmentScope
    fun provideYearlyListAdapter() = YearlyListAdapter()
}
