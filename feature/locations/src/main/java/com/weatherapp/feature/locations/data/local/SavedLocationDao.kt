package com.weatherapp.feature.locations.data.local

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface SavedLocationDao {

    @Query("SELECT * FROM saved_locations ORDER BY cityName ASC")
    fun pagingSource(): PagingSource<Int, SavedLocationEntity>

    @Query("SELECT * FROM saved_locations")
    suspend fun getAll(): List<SavedLocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: SavedLocationEntity)

    @Query("SELECT cityId FROM saved_locations")
    fun getSavedCityIds(): kotlinx.coroutines.flow.Flow<List<Int>>

    // NEW SAFE DELETE METHOD
    @Query("DELETE FROM saved_locations WHERE id = :id")
    suspend fun deleteById(id: Int)


    @Delete
    suspend fun delete(location: SavedLocationEntity)

    //  change to use database id
    @Query("SELECT * FROM saved_locations WHERE id = :id LIMIT 1")
    suspend fun getLocationById(id: Int): SavedLocationEntity?
}