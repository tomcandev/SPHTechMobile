package com.tomcandev.mobiledatausage.domain.model

data class QuarterRecordDomainModel(
    val id: Int,
    val volume: Double,
    val year: Int,
    val quarter: Int
)