package com.tomcandev.mobiledatausage.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tomcandev.mobiledatausage.data.constants.CONSTANTS
import com.tomcandev.mobiledatausage.data.model.YearlyDataModel
import io.reactivex.Observable

@Dao
interface YearlyDataDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(yearlyDataModel: YearlyDataModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(yearlyDataModels: List<YearlyDataModel>)

    @Query("SELECT * FROM ${CONSTANTS.TB_YEARLY_DATA_USAGE} LIMIT :limit OFFSET :offset")
    fun getYearlyDataList(offset: Int, limit: Int): Observable<List<YearlyDataModel>>
}