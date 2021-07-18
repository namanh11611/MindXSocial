package com.namanh.kotlinbase.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "source")
data class Source(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
)