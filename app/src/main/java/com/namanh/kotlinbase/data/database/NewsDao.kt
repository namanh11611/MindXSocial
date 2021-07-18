package com.namanh.kotlinbase.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.namanh.kotlinbase.data.model.News

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    suspend fun getAll(): List<News>

    @Query("SELECT * FROM news WHERE id LIKE :newsId")
    suspend fun getNewsById(newsId: Int): News

    @Query("SELECT * FROM news WHERE title LIKE :title LIMIT 1")
    suspend fun findByTitle(title: String): News

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: List<News>)

    @Query("DELETE FROM news")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(news: News)
}