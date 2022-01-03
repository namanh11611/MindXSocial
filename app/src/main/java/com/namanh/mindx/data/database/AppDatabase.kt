package com.namanh.mindx.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.namanh.mindx.data.model.News
import com.namanh.mindx.utils.AppUtils

@Database(entities = [News::class], version = AppUtils.DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}