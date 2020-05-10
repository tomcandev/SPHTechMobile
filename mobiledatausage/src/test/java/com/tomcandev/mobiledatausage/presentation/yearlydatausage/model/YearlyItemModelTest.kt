package com.tomcandev.mobiledatausage.presentation.yearlydatausage.model

import org.junit.Assert.*
import org.junit.Test

class YearlyItemModelTest {
    @Test
    fun testCreateModel() {
        val quarterItemModel = QuarterItemModel(
            volume = 1.2,
            quarter = 2
        )
        val sample = YearlyItemModel(
            volume = 1.0,
            year = 2020,
            quarters = listOf(quarterItemModel),
            isAnyDownVolume = true
        )
        assertEquals(sample.volume, 1.0, 0.01)
        assertEquals(sample.year, 2020)
        assertEquals(sample.isAnyDownVolume, true)
        assertEquals(sample.quarters, listOf(quarterItemModel))
    }
}