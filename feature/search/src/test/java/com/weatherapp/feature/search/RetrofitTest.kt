package com.weatherapp.feature.search

import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.weatherapp.core.network.api.WeatherApi
import kotlinx.coroutines.runBlocking

class RetrofitTest {
    @Test
    fun testApi() = runBlocking {
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            
            val api = retrofit.create(WeatherApi::class.java)
            val result = api.getWeatherByCity("Paris")
            println("SUCCESS: $result")
        } catch (e: Exception) {
            println("EXCEPTION: ${e.message}")
            e.printStackTrace()
        } catch (e: Throwable) {
            println("THROWABLE: ${e.message}")
            e.printStackTrace()
        }
    }
}
