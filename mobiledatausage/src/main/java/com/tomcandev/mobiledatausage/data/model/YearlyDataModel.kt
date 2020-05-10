package com.tomcandev.mobiledatausage.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.tomcandev.mobiledatausage.data.constants.CONSTANTS
import com.tomcandev.mobiledatausage.domain.model.QuarterRecordDomainModel

@Entity(tableName = CONSTANTS.TB_YEARLY_DATA_USAGE)
data class YearlyDataModel(
    @PrimaryKey
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("volume")
    val volume: Double,
    @field:SerializedName("year")
    val year: Int,
    @field:SerializedName("quarter")
    val quarter: Int
)

fun YearlyDataModel.toDomainModel(): QuarterRecordDomainModel {
    return QuarterRecordDomainModel(
        id = id,
        volume = volume,
        year = year,
        quarter = quarter
    )
}