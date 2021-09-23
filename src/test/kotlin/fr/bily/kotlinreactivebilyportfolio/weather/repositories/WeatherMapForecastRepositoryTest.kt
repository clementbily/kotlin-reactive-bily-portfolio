package fr.bily.kotlinreactivebilyportfolio.weather.repositories

import fr.bily.kotlinreactivebilyportfolio.weather.data.WeatherForecast
import fr.bily.kotlinreactivebilyportfolio.weather.data.defaultClearWeatherForecast
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.*
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class WeatherMapForecastRepositoryTest {

    @Mock
    private lateinit var exchangeFunction: ExchangeFunction

    @Captor
    private lateinit var captor: ArgumentCaptor<ClientRequest>

    private lateinit var builder: WebClient.Builder

    private lateinit var mockResponse: ClientResponse

    @BeforeAll
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @BeforeEach
    fun setup() {
        mockResponse = Mockito.mock(ClientResponse::class.java)
        Mockito.`when`(exchangeFunction.exchange(captor.capture())).thenReturn(Mono.just(mockResponse))
        builder = WebClient.builder().baseUrl("/base").exchangeFunction(exchangeFunction)
    }

    @Test
    fun getPointWeatherForecast() {
        val repository = WeatherMapForecastRepository(builder, "http://test-weather.ca", "secret")
        Mockito.`when`(mockResponse.bodyToMono(WeatherForecast::class.java)).thenReturn(Mono.just(defaultClearWeatherForecast))
        val weatherForecastMono = repository.getPointWeatherForecast(1.0f, -2.0f)
        StepVerifier.create(weatherForecastMono.log())
                .expectNext(defaultClearWeatherForecast)
                .expectComplete()
                .verify()
    }
}