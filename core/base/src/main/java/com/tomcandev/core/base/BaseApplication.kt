package com.tomcandev.core.base

import android.app.Application
import com.tomcandev.core.base.di.BaseComponent

abstract class BaseApplication : Application() {
    companion object {
        lateinit var baseComponent: BaseComponent
    }

    override fun onCreate() {
        super.onCreate()
        initAppDependencyInjection()
    }

    private fun initAppDependencyInjection() {
        baseComponent = BaseComponent.create(this)
    }
}