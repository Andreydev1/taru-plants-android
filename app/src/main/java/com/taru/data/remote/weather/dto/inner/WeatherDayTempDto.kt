package com.taru.data.remote.weather.dto.inner

import com.squareup.moshi.JsonClass

/**
 * Created by Niraj on 17-01-2023.
 */
@JsonClass(generateAdapter = true)
data class WeatherDayTempDto(val min: Float, val max: Float) {
}