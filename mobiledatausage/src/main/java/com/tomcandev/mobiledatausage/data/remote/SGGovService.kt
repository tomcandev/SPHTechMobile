package com.tomcandev.mobiledatausage.data.remote

import com.tomcandev.mobiledatausage.data.model.DataStoreResponseModel
import com.tomcandev.mobiledatausage.data.model.QuarterResponseModel
import com.tomcandev.mobiledatausage.data.model.ResultResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SGGovService {
    @GET("/api/action/datastore_search")
    fun getDataStoreSearch(
        @Query("resource_id") resourceId: String,
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): Observable<DataStoreResponseModel<ResultResponseModel<QuarterResponseModel>>>
}