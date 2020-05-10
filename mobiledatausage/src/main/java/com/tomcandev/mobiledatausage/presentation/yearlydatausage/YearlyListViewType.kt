package com.tomcandev.mobiledatausage.presentation.yearlydatausage

import com.tomcandev.mobiledatausage.presentation.yearlydatausage.model.YearlyItemModel

sealed class YearlyListViewType {
    data class YearlyItem(var yearlyItemModel: YearlyItemModel) : YearlyListViewType()
}