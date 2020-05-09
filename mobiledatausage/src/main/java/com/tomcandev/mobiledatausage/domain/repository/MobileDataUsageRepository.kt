package com.tomcandev.mobiledatausage.domain.repository

import com.tomcandev.mobiledatausage.domain.model.QuarterRecordDomainModel
import io.reactivex.Observable

interface MobileDataUsageRepository {
    fun getMobileDataUsageList(offset: Int, limit: Int): Observable<List<QuarterRecordDomainModel>>
}