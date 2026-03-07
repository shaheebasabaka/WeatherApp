package com.weatherapp.feature.locations.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_locations")
data class SavedLocationEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val cityId: Int,
    val cityName: String,
    val temperature: Double,
    val condition: String,
    val icon: String,

    val lat: Double,
    val lon: Double
)