package com.example.happyplacesapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HappyPlaceDao {

    @Query("SELECT * FROM happy_places")
    fun getAllPlaces(): Flow<List<HappyPlace>>

    @Insert
    suspend fun insert(place: HappyPlace)

    @Update
    suspend fun update(place: HappyPlace)

    @Delete
    suspend fun delete(place: HappyPlace)



}
