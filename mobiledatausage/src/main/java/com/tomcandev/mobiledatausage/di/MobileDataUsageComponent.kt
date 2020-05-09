package com.tomcandev.mobiledatausage.di

import com.tomcandev.core.base.BaseApplication
import com.tomcandev.core.base.di.BaseComponent
import com.tomcandev.core.base.di.scope.FeatureScope
import com.tomcandev.mobiledatausage.presentation.yearlydatausage.di.YearlyDataUsageComponent
import com.tomcandev.mobiledatausage.presentation.yearlydatausage.di.YearlyDataUsageModule
import dagger.Component

@Component(
    modules = [DataModule::class],
    dependencies = [BaseComponent::class]
)
@FeatureScope
interface MobileDataUsageComponent {
    fun plus(yearlyDataUsageModule: YearlyDataUsageModule): YearlyDataUsageComponent

    companion object {
        fun init(): MobileDataUsageComponent =
            DaggerMobileDataUsageComponent.builder().baseComponent(BaseApplication.baseComponent).build()
    }
}