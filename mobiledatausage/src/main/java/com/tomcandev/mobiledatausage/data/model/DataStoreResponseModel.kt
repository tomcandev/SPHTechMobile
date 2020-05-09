package com.tomcandev.mobiledatausage.data.model

import com.google.gson.annotations.SerializedName

data class DataStoreResponseModel<T> (
    @SerializedName("help")
    val help: String,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("result")
    val result: T
)