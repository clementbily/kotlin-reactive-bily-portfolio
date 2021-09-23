package fr.bily.kotlinreactivebilyportfolio.weather.repositories

import fr.bily.kotlinreactivebilyportfolio.weather.data.WeatherForecast
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Repository
class WeatherMapForecastRepository(webClientBuilder: WebClient.Builder,
                                   @Value("\${weather.api.url}") apiUrl: String, @Value("\${weather.api.secret}") private val apiSecret: String) : WeatherForecastRepository {
    private val webClient: WebClient = webClientBuilder.baseUrl("${apiUrl}/data/2.5").build()
    override fun getPointWeatherForecast(lon: Float, lat: Float): Mono<WeatherForecast> {
        return webClient.get().uri { uriBuilder ->
            uriBuilder.path("/weather")
                    .queryParam("appid", apiSecret)
                    .queryParam("lat", lat)
                    .queryParam("lon", lon)
                    .build()
        }.retrieve().bodyToMono(WeatherForecast::class.java)
    }
}