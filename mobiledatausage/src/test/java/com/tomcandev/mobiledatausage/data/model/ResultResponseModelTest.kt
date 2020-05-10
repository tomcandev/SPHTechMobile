package com.tomcandev.mobiledatausage.data.model

import org.junit.Assert.*
import org.junit.Test

class ResultResponseModelTest {
    @Test
    fun testCreateModel() {
        val sample = ResultResponseModel(resourceId = "123456789", records = listOf(1, 2, 3), limit = 5, total = 5)
        assertEquals("123456789", sample.resourceId)
        assertEquals(listOf(1, 2, 3), sample.records)
        assertEquals(5, sample.limit)
        assertEquals(5, sample.total)
    }
}