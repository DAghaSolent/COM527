package com.example.roomappbasic

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Hit::class), version = 1, exportSchema = false)
public abstract class HittasticDatabase : RoomDatabase() {

    abstract fun hitDao(): HitDao

    companion object {

        private var INSTANCE: HittasticDatabase? = null

        fun getDatabase(ctx: Context): HittasticDatabase {

            var tmpInstance = INSTANCE
            if (tmpInstance == null) {
                tmpInstance = Room.databaseBuilder(
                    ctx.applicationContext,
                    HittasticDatabase::class.java,
                    "roomapp1"
                ).build()
                INSTANCE = tmpInstance
            }
            return tmpInstance
        }
    }
}