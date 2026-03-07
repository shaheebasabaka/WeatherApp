package com.weatherapp.feature.locations.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SavedLocationEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun savedLocationDao(): SavedLocationDao
}
