package com.tomcandev.core.base.di.module

import android.app.Application
import android.content.Context
import com.tomcandev.core.base.di.scope.AppScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseModule {
    @AppScope
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext
}