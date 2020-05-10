package com.tomcandev.mobiledatausage.data.repository

import com.tomcandev.mobiledatausage.db.MobileDataUsageDB
import com.tomcandev.mobiledatausage.data.model.toDataModel
import com.tomcandev.mobiledatausage.data.model.toDomainModel
import com.tomcandev.mobiledatausage.data.remote.SGGovService
import com.tomcandev.mobiledatausage.db.YearlyDataDAO
import com.tomcandev.mobiledatausage.domain.constants.CONSTANTS
import com.tomcandev.mobiledatausage.domain.model.QuarterRecordDomainModel
import com.tomcandev.mobiledatausage.domain.repository.MobileDataUsageRepository
import io.reactivex.Observable

class MobileDataUsageRepositoryImpl(
    private val sgGovService: SGGovService,
    private val yearlyDataDAO: YearlyDataDAO
) :
    MobileDataUsageRepository {
    override fun getMobileDataUsageList(
        offset: Int,
        limit: Int
    ): Observable<List<QuarterRecordDomainModel>> {
        val local = yearlyDataDAO.getYearlyDataList(offset, limit)
            .map { response -> response.map { item -> item.toDomainModel() } }
        return sgGovService.getDataStoreSearch(CONSTANTS.DATA_STORE_RESOURCE_ID, offset, limit)
            .map { response ->
                yearlyDataDAO
                    .insertAll(response.result.records.map { it.toDataModel() })
                response.result.records.map { it.toDomainModel() }
            }
            .onErrorResumeNext(local)
    }
}