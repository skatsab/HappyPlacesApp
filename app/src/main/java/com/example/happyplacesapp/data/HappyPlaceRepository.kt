package com.example.happyplacesapp.data

class HappyPlaceRepository(private val dao: HappyPlaceDao) {

    fun getAllPlaces() = dao.getAllPlaces()

    suspend fun insert(place: HappyPlace) = dao.insert(place)

    suspend fun update(place: HappyPlace) = dao.update(place)

    suspend fun delete(place: HappyPlace) = dao.delete(place)



}
