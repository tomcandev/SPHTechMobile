package com.tomcandev.mobiledatausage.presentation.yearlydatausage.di

import com.tomcandev.core.base.di.scope.FragmentScope
import com.tomcandev.mobiledatausage.di.DataModule
import com.tomcandev.mobiledatausage.di.MobileDataUsageComponent
import com.tomcandev.mobiledatausage.presentation.yearlydatausage.YearlyListFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [YearlyDataUsageModule::class]
)
@FragmentScope
interface YearlyDataUsageComponent {
    fun inject(yearlyListFragment: YearlyListFragment)

    companion object {
        fun init(): YearlyDataUsageComponent = MobileDataUsageComponent.init().plus(YearlyDataUsageModule())
    }
}