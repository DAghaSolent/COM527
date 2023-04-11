package com.example.roomapp
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Song::class), version = 1, exportSchema = false)
abstract class HittasticDatabase: RoomDatabase() {
    abstract fun songDao(): SongDao

    companion object {
        private var instance: HittasticDatabase? = null

        fun getDatabase(ctx:Context) : HittasticDatabase {
            var tmpInstance = instance
            if(tmpInstance == null) {
                tmpInstance = Room.databaseBuilder(
                    ctx.applicationContext,
                    HittasticDatabase::class.java,
                    "studentDatabase"
                ).build()
                instance = tmpInstance
            }
            return tmpInstance
        }
    }
}