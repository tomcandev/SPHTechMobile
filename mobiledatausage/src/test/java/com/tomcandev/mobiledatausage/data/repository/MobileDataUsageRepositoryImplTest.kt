package com.tomcandev.mobiledatausage.data.repository

import com.tomcandev.mobiledatausage.data.model.DataStoreResponseModel
import com.tomcandev.mobiledatausage.data.model.QuarterResponseModel
import com.tomcandev.mobiledatausage.data.model.ResultResponseModel
import com.tomcandev.mobiledatausage.data.model.YearlyDataModel
import com.tomcandev.mobiledatausage.data.remote.SGGovService
import com.tomcandev.mobiledatausage.db.YearlyDataDAO
import com.tomcandev.mobiledatausage.domain.constants.CONSTANTS
import com.tomcandev.mobiledatausage.domain.model.QuarterRecordDomainModel
import com.tomcandev.mobiledatausage.domain.repository.MobileDataUsageRepository
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class MobileDataUsageRepositoryImplTest {
    private val yearlyDataDAO = Mockito.mock(YearlyDataDAO::class.java)
    private val sgGovService = Mockito.mock(SGGovService::class.java)
    private val repo: MobileDataUsageRepository =
        MobileDataUsageRepositoryImpl(sgGovService, yearlyDataDAO)

    @Test
    fun loadData() {
        val listLocalData =
            listOf(YearlyDataModel(id = 1, volume = 1.001, year = 2020, quarter = 1))
        Mockito.`when`(yearlyDataDAO.getYearlyDataList(0, 1))
            .thenReturn(Observable.just(listLocalData))
        
        val remoteData = DataStoreResponseModel(
            help = "help",
            success = true,
            result = ResultResponseModel(
                resourceId = "123456789",
                records = listOf(
                    QuarterResponseModel(
                        volumeOfMobileData = 1.001,
                        quarter = "2020-Q1",
                        id = 1
                    )
                ),
                limit = 1,
                total = 5
            )
        )
        Mockito.`when`(sgGovService.getDataStoreSearch(CONSTANTS.DATA_STORE_RESOURCE_ID, 0, 1))
            .thenReturn(Observable.just(remoteData))

        val subscriber = TestObserver<List<QuarterRecordDomainModel>>()
        repo.getMobileDataUsageList(0, 1)
            .subscribe(subscriber)

        subscriber.assertComplete()
        subscriber.assertNoErrors()
        subscriber.assertValueCount(1)
    }
}