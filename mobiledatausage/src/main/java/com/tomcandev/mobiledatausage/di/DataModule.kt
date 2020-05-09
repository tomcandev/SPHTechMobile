package com.tomcandev.mobiledatausage.di

import com.tomcandev.mobiledatausage.data.repository.MobileDataUsageRepositoryImpl
import com.tomcandev.mobiledatausage.data.remote.SGGovService
import com.tomcandev.mobiledatausage.domain.repository.MobileDataUsageRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DataModule {
    @Provides
    fun provideMobileDataUsageRepository(
        sgGovService: SGGovService
    ): MobileDataUsageRepository =
        MobileDataUsageRepositoryImpl(
            sgGovService
        )

    @Provides
    fun provideSGGovService(retrofit: Retrofit): SGGovService =
        retrofit.create(SGGovService::class.java)
}
