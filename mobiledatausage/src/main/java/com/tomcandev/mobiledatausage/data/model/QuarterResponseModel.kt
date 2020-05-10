package com.tomcandev.mobiledatausage.data.model

import com.google.gson.annotations.SerializedName
import com.tomcandev.mobiledatausage.domain.model.QuarterRecordDomainModel

data class QuarterResponseModel(
    @SerializedName("volume_of_mobile_data")
    val volumeOfMobileData: Double,
    @SerializedName("quarter")
    val quarter: String,
    @SerializedName("_id")
    val id: Int
)

fun QuarterResponseModel.toDomainModel(): QuarterRecordDomainModel {
    val arrString = quarter.split("-")
    var yearInt = 0
    var quarterInt = 0
    if (arrString.size == 2) {
        yearInt = arrString[0].toInt()
        quarterInt = arrString[1].takeLast(1).toInt()
    }
    return QuarterRecordDomainModel(
        id = id,
        volume = volumeOfMobileData,
        year = yearInt,
        quarter = quarterInt
    )
}

fun QuarterResponseModel.toDataModel(): YearlyDataModel {
    val arrString = quarter.split("-")
    var yearInt = 0
    var quarterInt = 0
    if (arrString.size == 2) {
        yearInt = arrString[0].toInt()
        quarterInt = arrString[1].takeLast(1).toInt()
    }
    return YearlyDataModel(
        id = id,
        volume = volumeOfMobileData,
        year = yearInt,
        quarter = quarterInt
    )
}