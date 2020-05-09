package com.tomcandev.mobiledatausage.data.repository

import com.tomcandev.mobiledatausage.data.model.toDomainModel
import com.tomcandev.mobiledatausage.data.remote.SGGovService
import com.tomcandev.mobiledatausage.domain.constants.CONSTANTS
import com.tomcandev.mobiledatausage.domain.model.QuarterRecordDomainModel
import com.tomcandev.mobiledatausage.domain.repository.MobileDataUsageRepository
import io.reactivex.Observable

class MobileDataUsageRepositoryImpl(private val sgGovService: SGGovService) :
    MobileDataUsageRepository {
    override fun getMobileDataUsageList(
        offset: Int,
        limit: Int
    ): Observable<List<QuarterRecordDomainModel>> {
        return sgGovService.getDataStoreSearch(CONSTANTS.DATA_STORE_RESOURCE_ID, offset, limit)
            .map { response -> response.result.records.map { it.toDomainModel() } }
    }
}