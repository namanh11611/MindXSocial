package com.namanh.kotlinbase.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.namanh.kotlinbase.utils.AppUtils

@Database(entities = [NewsData::class], version = AppUtils.DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}