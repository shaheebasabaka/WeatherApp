package com.weatherapp.feature.locations.data.di

import android.content.Context
import androidx.room.Room
import com.weatherapp.feature.locations.data.local.SavedLocationDao
import com.weatherapp.feature.locations.data.local.WeatherDatabase
import com.weatherapp.feature.locations.data.repository.LocationsRepositoryImpl
import com.weatherapp.feature.locations.domain.repository.LocationsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationsModule {

    @Binds
    abstract fun bindLocationsRepository(
        impl: LocationsRepositoryImpl
    ): LocationsRepository

    companion object {
        @Provides
        @Singleton
        fun provideWeatherDatabase(
            @ApplicationContext context: Context
        ): WeatherDatabase {
            return Room.databaseBuilder(
                context,
                WeatherDatabase::class.java,
                "weather_database"
            ).fallbackToDestructiveMigration()
             .build()
        }

        @Provides
        fun provideSavedLocationDao(database: WeatherDatabase): SavedLocationDao {
            return database.savedLocationDao()
        }
    }
}
