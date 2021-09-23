package fr.bily.kotlinreactivebilyportfolio.weather.repositories


import fr.bily.kotlinreactivebilyportfolio.weather.data.WeatherForecast
import reactor.core.publisher.Mono

interface WeatherForecastRepository {
    /**
     * return the weather forecast for the given longitude and latitude
     */
    fun getPointWeatherForecast(lon: Float, lat: Float): Mono<WeatherForecast>
}