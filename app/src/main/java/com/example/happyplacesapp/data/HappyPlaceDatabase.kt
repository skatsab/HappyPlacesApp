package com.example.happyplacesapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HappyPlace::class], version = 1, exportSchema = false)

abstract class HappyPlaceDatabase : RoomDatabase() {

    abstract fun happyPlaceDao(): HappyPlaceDao

    companion object {
        @Volatile
        private var INSTANCE: HappyPlaceDatabase? = null

        fun getInstance(context: Context): HappyPlaceDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HappyPlaceDatabase::class.java,
                    "happy_places_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
