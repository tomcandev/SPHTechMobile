package com.tomcandev.mobiledatausage.presentation.yearlydatausage.model

import com.tomcandev.mobiledatausage.domain.model.QuarterRecordDomainModel
import org.junit.Assert.*
import org.junit.Test

class QuarterItemModelTest {
    @Test
    fun testCreateModel() {
        val sample = QuarterItemModel(
            volume = 1.0,
            quarter = 1
        )
        assertEquals(sample.volume, 1.0, 0.01)
        assertEquals(sample.quarter, 1)
    }
}