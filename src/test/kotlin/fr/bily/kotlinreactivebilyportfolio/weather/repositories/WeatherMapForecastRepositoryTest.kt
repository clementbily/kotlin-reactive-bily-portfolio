package fr.bily.kotlinreactivebilyportfolio.weather.repositories

import fr.bily.kotlinreactivebilyportfolio.weather.data.WeatherForecast
import fr.bily.kotlinreactivebilyportfolio.weather.data.defaultClearWeatherForecast
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriBuilder
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.net.URI
import java.util.function.Function


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension::class)
internal class WeatherMapForecastRepositoryTest {

    @Mock
    private lateinit var builder: WebClient.Builder

    @Mock
    private lateinit var webClient: WebClient

    @Mock
    private lateinit var uriSpec: WebClient.RequestHeadersUriSpec<*>

    @Mock
    private lateinit var headerSpec: WebClient.RequestHeadersSpec<*>

    @Mock
    private lateinit var mockResponse: WebClient.ResponseSpec


    @BeforeEach
    fun setup() {
        `when`(builder.baseUrl(any())).thenReturn(builder)
        `when`(builder.build()).thenReturn(webClient)
        `when`(webClient.get()).thenReturn(uriSpec)
        doReturn(headerSpec).`when`(uriSpec).uri(any<Function<UriBuilder, URI>>())
        `when`(headerSpec.retrieve()).thenReturn(mockResponse)

    }

    @Test
    fun getPointWeatherForecast() {
        val repository = WeatherMapForecastRepository(builder, "http://test-weather.ca", "secret")
        `when`(mockResponse.bodyToMono(WeatherForecast::class.java)).thenReturn(Mono.just(defaultClearWeatherForecast))
        val weatherForecastMono = repository.getPointWeatherForecast(1.0f, -2.0f)
        StepVerifier.create(weatherForecastMono.log())
                .expectNext(defaultClearWeatherForecast)
                .expectComplete()
                .verify()
    }
}
