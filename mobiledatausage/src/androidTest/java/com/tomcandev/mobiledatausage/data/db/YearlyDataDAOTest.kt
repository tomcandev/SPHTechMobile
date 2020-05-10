package com.tomcandev.mobiledatausage.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tomcandev.mobiledatausage.data.model.YearlyDataModel
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class YearlyDataDAOTest : MobileDataUsageDBTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndLoad() {
        val yearlyDataModel = YearlyDataModel(id = 1, volume = 1.0, year = 2020, quarter = 1)
        db.yearlyDataDAO().insert(yearlyDataModel)

        val scheduler = TestScheduler()
        val subscriber = TestObserver<List<YearlyDataModel>>()
        db.yearlyDataDAO().getYearlyDataList(0, 5)
            .subscribeOn(scheduler)
            .subscribe(subscriber)

        subscriber.assertComplete()
        subscriber.assertNoErrors()
        subscriber.assertValueCount(1)
    }
}