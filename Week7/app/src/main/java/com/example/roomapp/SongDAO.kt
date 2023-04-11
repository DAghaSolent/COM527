package com.example.roomapp
import androidx.room.*

@Dao
interface SongDao {

    @Query("SELECT * FROM songs WHERE id= :id")
    fun getSongById(id: Long): Song?

    @Query("SELECT* FROM songs")
    fun getAllSongs(): List <Song>

    @Insert
    fun insert(song: Song) : Long

    @Update
    fun update(song: Song) : Int

    @Delete
    fun delete(song: Song) : Int
}