package com.taru.data.remote.plants

import com.taru.BuildConfig
import com.taru.data.remote.plants.dto.PlantsSearchDto
import com.taru.data.remote.weather.dto.WeatherCurrentDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Niraj on 22-01-2023.
 */
interface ApiPlants {
    @GET("plants/search")
    suspend fun byQ(@Query("q") searchQ: String?,
                           @Query("page") page: Int = 0,
                    @Query("token") token: String = BuildConfig.TREFLE_KEY
    ): Response<PlantsSearchDto>

    @GET("plants")
    suspend fun default(
                    @Query("page") page: Int = 0,
                    @Query("token") token: String = BuildConfig.TREFLE_KEY
    ): Response<PlantsSearchDto>

}