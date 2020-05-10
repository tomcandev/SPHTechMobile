package com.tomcandev.mobiledatausage.presentation.utils

import com.tomcandev.mobiledatausage.domain.model.QuarterRecordDomainModel
import com.tomcandev.mobiledatausage.presentation.yearlydatausage.model.QuarterItemModel

object Utils {
    fun isDownAnyQuarterByDomainModel(list: List<QuarterRecordDomainModel>): Boolean {
        for (i in 1 until list.size) {
            if (list[i].volume < list[i - 1].volume) {
                return true
            }
        }
        return false
    }

    fun isDownAnyQuarterByItemModel(list: List<QuarterItemModel>): Boolean {
        for (i in 1 until list.size) {
            if (list[i].volume < list[i - 1].volume) {
                return true
            }
        }
        return false
    }
}