package com.tomcandev.core.base.di

import android.app.Application
import android.content.Context
import com.tomcandev.core.base.di.module.BaseModule
import com.tomcandev.core.base.di.module.NetworkModule
import com.tomcandev.core.base.di.scope.AppScope
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@AppScope
@Component(
    modules = [BaseModule::class, NetworkModule::class]
)
interface BaseComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): BaseComponent
    }

    companion object {
        fun create(application: Application): BaseComponent =
            DaggerBaseComponent.builder().application(application).build()
    }

    val retrofit: Retrofit
    val context: Context
}