package com.tomcandev.mobiledatausage.data.model

import com.tomcandev.mobiledatausage.domain.model.QuarterRecordDomainModel
import org.junit.Assert.*
import org.junit.Test

class QuarterResponseModelTest {
    @Test
    fun testCreateModel() {
        val sample = QuarterResponseModel(volumeOfMobileData = 1.001, quarter = "2020-Q1", id = 1)
        assertEquals(1.001, sample.volumeOfMobileData, 0.01)
        assertEquals("2020-Q1", sample.quarter)
        assertEquals(1, sample.id)
    }

    @Test
    fun testConvertToDomainModel() {
        val sample = QuarterResponseModel(volumeOfMobileData = 1.001, quarter = "2020-Q1", id = 1)
        assertEquals(
            QuarterRecordDomainModel(id = 1, volume = 1.001, year = 2020, quarter = 1),
            sample.toDomainModel()
        )
    }

    @Test
    fun testConvertToDataModel() {
        val sample = QuarterResponseModel(volumeOfMobileData = 1.001, quarter = "2020-Q1", id = 1)
        assertEquals(
            YearlyDataModel(id = 1, volume = 1.001, year = 2020, quarter = 1),
            sample.toDataModel()
        )
    }
}