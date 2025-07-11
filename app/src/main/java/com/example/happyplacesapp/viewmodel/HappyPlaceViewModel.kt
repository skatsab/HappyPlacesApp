package com.example.happyplacesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyplacesapp.data.HappyPlace
import com.example.happyplacesapp.data.HappyPlaceRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HappyPlaceViewModel(
    private val repository: HappyPlaceRepository
) : ViewModel() {

    // UI-State: Liste aller Orte
    private val _places = MutableStateFlow<List<HappyPlace>>(emptyList())
    val places: StateFlow<List<HappyPlace>> = _places.asStateFlow()

    // Aktueller Ort (z. B. für DetailScreen)
    private val _selectedPlace = MutableStateFlow<HappyPlace?>(null)
    val selectedPlace: StateFlow<HappyPlace?> = _selectedPlace.asStateFlow()

    init {
        loadPlaces()
    }

    fun loadPlaces() {
        viewModelScope.launch {
            repository.getAllPlaces()
                .collect { placeList ->
                    _places.value = placeList
                }
        }
    }

    fun addPlace(place: HappyPlace) {
        viewModelScope.launch {
            repository.insert(place)
        }
    }

    fun insert(place: HappyPlace) {
        viewModelScope.launch {
            repository.insert(place)
        }
    }


    fun updatePlace(place: HappyPlace) {
        viewModelScope.launch {
            repository.update(place)
        }
    }


    fun deletePlace(place: HappyPlace) {
        viewModelScope.launch {
            repository.delete(place)
        }
    }

    fun selectPlace(place: HappyPlace?) {
        _selectedPlace.value = place
    }
}
