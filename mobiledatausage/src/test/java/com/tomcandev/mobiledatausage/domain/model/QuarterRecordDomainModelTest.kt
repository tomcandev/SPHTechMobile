package com.tomcandev.mobiledatausage.domain.model

import com.tomcandev.mobiledatausage.data.model.YearlyDataModel
import org.junit.Assert.*
import org.junit.Test

class QuarterRecordDomainModelTest {
    @Test
    fun testCreateModel() {
        val sample = QuarterRecordDomainModel(id = 1, volume = 1.001, year = 2020, quarter = 1)
        assertEquals(1, sample.id)
        assertEquals(1.001, sample.volume, 0.01)
        assertEquals(2020, sample.year)
        assertEquals(1, sample.quarter)
    }
}