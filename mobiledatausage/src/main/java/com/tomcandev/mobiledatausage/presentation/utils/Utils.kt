package com.tomcandev.mobiledatausage.presentation.utils

import com.tomcandev.mobiledatausage.domain.model.QuarterRecordDomainModel

object Utils {
    fun isDownAnyQuarter(list: List<QuarterRecordDomainModel>): Boolean {
        for (i in 1 until list.size) {
            if (list[i].volume < list[i - 1].volume) {
                return true
            }
        }
        return false
    }
}