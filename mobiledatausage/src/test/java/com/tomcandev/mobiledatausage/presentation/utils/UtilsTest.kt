package com.tomcandev.mobiledatausage.presentation.utils

import com.tomcandev.mobiledatausage.domain.model.QuarterRecordDomainModel
import com.tomcandev.mobiledatausage.presentation.yearlydatausage.model.QuarterItemModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test

class UtilsTest {
    val listWithDown = listOf(
        QuarterRecordDomainModel(
            id = 1,
            volume = 1.0,
            year = 2020,
            quarter = 1
        ),
        QuarterRecordDomainModel(
            id = 2,
            volume = 1.2,
            year = 2020,
            quarter = 2
        ),
        QuarterRecordDomainModel(
            id = 3,
            volume = 1.1,
            year = 2020,
            quarter = 3
        ),
        QuarterRecordDomainModel(
            id = 4,
            volume = 1.5,
            year = 2020,
            quarter = 4
        )
    )
    val listWithoutDown = listOf(
        QuarterRecordDomainModel(
            id = 1,
            volume = 1.0,
            year = 2020,
            quarter = 1
        ),
        QuarterRecordDomainModel(
            id = 2,
            volume = 1.2,
            year = 2020,
            quarter = 2
        ),
        QuarterRecordDomainModel(
            id = 3,
            volume = 1.3,
            year = 2020,
            quarter = 3
        ),
        QuarterRecordDomainModel(
            id = 4,
            volume = 1.5,
            year = 2020,
            quarter = 4
        )
    )
    val listQuarterItemModelWithDown = listOf(
        QuarterItemModel(
            volume = 1.0,
            quarter = 1
        ),
        QuarterItemModel(
            volume = 1.2,
            quarter = 2
        ),
        QuarterItemModel(
            volume = 1.1,
            quarter = 3
        ),
        QuarterItemModel(
            volume = 1.5,
            quarter = 4
        )
    )
    val listQuarterItemModelWithoutDown = listOf(
        QuarterItemModel(
            volume = 1.0,
            quarter = 1
        ),
        QuarterItemModel(
            volume = 1.2,
            quarter = 2
        ),
        QuarterItemModel(
            volume = 1.3,
            quarter = 3
        ),
        QuarterItemModel(
            volume = 1.5,
            quarter = 4
        )
    )
    @Test
    fun isDownAnyQuarterByDomainModel() {
        assertEquals(true, Utils.isDownAnyQuarterByDomainModel(listWithDown))
        assertEquals(false, Utils.isDownAnyQuarterByDomainModel(listWithoutDown))
    }

    @Test
    fun isDownAnyQuarterByItemModel() {
        assertEquals(true, Utils.isDownAnyQuarterByItemModel(listQuarterItemModelWithDown))
        assertEquals(false, Utils.isDownAnyQuarterByItemModel(listQuarterItemModelWithoutDown))
    }
}