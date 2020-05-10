package com.tomcandev.mobiledatausage.data.model

import com.tomcandev.mobiledatausage.presentation.utils.Utils
import org.junit.Assert.*
import org.junit.Test

class DataStoreResponseModelTest {
    @Test
    fun testCreateModel() {
        val sample = DataStoreResponseModel(help = "help", success = true, result = "result")
        assertEquals("help", sample.help)
        assertEquals(true, sample.success)
        assertEquals("result", sample.result)
    }
}