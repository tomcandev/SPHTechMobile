package com.tomcandev.mobiledatausage.data.model

import com.google.gson.annotations.SerializedName

data class ResultResponseModel<T>(
    @SerializedName("resourceId")
    val resourceId: String,
    @SerializedName("records")
    val records: List<T>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("total")
    val total: Int
)