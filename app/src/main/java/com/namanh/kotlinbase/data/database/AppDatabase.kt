package com.namanh.kotlinbase.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.namanh.kotlinbase.data.model.News
import com.namanh.kotlinbase.utils.AppUtils

@Database(entities = [News::class], version = AppUtils.DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}