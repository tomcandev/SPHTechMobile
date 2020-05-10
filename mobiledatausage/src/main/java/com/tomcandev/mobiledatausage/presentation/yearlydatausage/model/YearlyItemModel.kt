package com.tomcandev.mobiledatausage.presentation.yearlydatausage.model

data class YearlyItemModel(
    val volume: Double,
    val year: Int,
    val quarters: List<QuarterItemModel>,
    val isAnyDownVolume: Boolean
)