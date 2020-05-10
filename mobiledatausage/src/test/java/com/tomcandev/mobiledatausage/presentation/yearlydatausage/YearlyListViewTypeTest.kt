package com.tomcandev.mobiledatausage.presentation.yearlydatausage

import com.tomcandev.mobiledatausage.presentation.utils.NetworkState
import com.tomcandev.mobiledatausage.presentation.yearlydatausage.model.QuarterItemModel
import com.tomcandev.mobiledatausage.presentation.yearlydatausage.model.YearlyItemModel
import org.junit.Assert.*
import org.junit.Test

class YearlyListViewTypeTest {
    @Test
    fun testCreateViewType() {
        val quarterItemModel = QuarterItemModel(
            volume = 1.2,
            quarter = 2
        )
        val yearlyItemModel = YearlyItemModel(
            volume = 1.0,
            year = 2020,
            quarters = listOf(quarterItemModel),
            isAnyDownVolume = true
        )

        val yearlyListViewType: YearlyListViewType = YearlyListViewType.YearlyItem(yearlyItemModel)
        assertEquals(
            (yearlyListViewType as YearlyListViewType.YearlyItem).yearlyItemModel,
            yearlyItemModel
        )
    }
}