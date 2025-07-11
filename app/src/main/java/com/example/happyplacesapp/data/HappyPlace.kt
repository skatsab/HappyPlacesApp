package com.example.happyplacesapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "happy_places")
data class HappyPlace(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val imageUri: String = "",
    val notes: String = ""
) : Parcelable
