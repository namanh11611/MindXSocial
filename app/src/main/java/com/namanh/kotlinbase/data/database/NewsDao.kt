package com.namanh.kotlinbase.data.database

import androidx.room.*

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    suspend fun getAll(): List<NewsData>

    @Query("SELECT * FROM news WHERE id LIKE :newsId")
    suspend fun getNewsById(newsId: Int): NewsData

    @Query("SELECT * FROM news WHERE title LIKE :title LIMIT 1")
    suspend fun findByTitle(title: String): NewsData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg news: NewsData)

    @Delete
    suspend fun delete(news: NewsData)
}