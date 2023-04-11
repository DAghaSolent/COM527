package com.example.roomappbasic

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HitDao {

    @Query("SELECT * FROM hits WHERE id=:id")
    fun getHitById(id: Long) : Hit

    @Query("SELECT * FROM hits")
    fun getAllHits(): LiveData<List<Hit>>

    @Insert
    suspend fun insert(hit: Hit) : Long

    @Query("SELECT * FROM hits WHERE artist=:artist")
    fun getHitsByArtist(artist: String) : List<Hit>

    @Delete
    suspend fun delete(hit: Hit)

    @Update
    suspend fun update(hit: Hit)
}

