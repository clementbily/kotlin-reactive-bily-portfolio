package fr.bily.kotlinreactivebilyportfolio.weather.repositories


import fr.bily.kotlinreactivebilyportfolio.weather.data.WeatherForecast
import reactor.core.publisher.Mono

interface WeatherForecastRepository {
    fun getPointWeatherForecast(lon: Float, lat: Float): Mono<WeatherForecast>
}