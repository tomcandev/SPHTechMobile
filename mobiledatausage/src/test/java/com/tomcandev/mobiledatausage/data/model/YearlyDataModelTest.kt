package com.tomcandev.mobiledatausage.data.model

import com.tomcandev.mobiledatausage.domain.model.QuarterRecordDomainModel
import org.junit.Assert.*
import org.junit.Test

class YearlyDataModelTest {
    @Test
    fun testCreateModel() {
        val sample = YearlyDataModel(id = 1, volume = 1.001, year = 2020, quarter = 1)
        assertEquals(1, sample.id)
        assertEquals(1.001, sample.volume, 0.01)
        assertEquals(2020, sample.year)
        assertEquals(1, sample.quarter)
    }

    @Test
    fun testConvertToDomainModel() {
        val sample = YearlyDataModel(id = 1, volume = 1.001, year = 2020, quarter = 1)
        assertEquals(
            QuarterRecordDomainModel(id = 1, volume = 1.001, year = 2020, quarter = 1),
            sample.toDomainModel()
        )
    }
}