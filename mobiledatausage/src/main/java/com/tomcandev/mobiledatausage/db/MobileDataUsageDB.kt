package com.tomcandev.mobiledatausage.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tomcandev.mobiledatausage.data.constants.CONSTANTS
import com.tomcandev.mobiledatausage.data.model.YearlyDataModel

@Database(entities = [YearlyDataModel::class], version = 1, exportSchema = false)
abstract class MobileDataUsageDB : RoomDatabase() {
    abstract fun yearlyDataDAO(): YearlyDataDAO

    companion object {
        fun create(context: Context): MobileDataUsageDB {
            return Room.databaseBuilder(context, MobileDataUsageDB::class.java, CONSTANTS.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
