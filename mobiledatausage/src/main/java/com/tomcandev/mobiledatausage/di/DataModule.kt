package com.tomcandev.mobiledatausage.di

import android.content.Context
import com.tomcandev.mobiledatausage.db.MobileDataUsageDB
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
        sgGovService: SGGovService,
        mobileDataUsageDB: MobileDataUsageDB
    ): MobileDataUsageRepository =
        MobileDataUsageRepositoryImpl(
            sgGovService,
            mobileDataUsageDB.yearlyDataDAO()
        )

    @Provides
    fun provideSGGovService(retrofit: Retrofit): SGGovService =
        retrofit.create(SGGovService::class.java)

    @Provides
    fun provideMobileDataUsageDB(context: Context): MobileDataUsageDB =
        MobileDataUsageDB.create(context)
}
