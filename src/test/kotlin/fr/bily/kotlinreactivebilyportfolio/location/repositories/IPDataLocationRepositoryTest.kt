package fr.bily.kotlinreactivebilyportfolio.location.repositories

import fr.bily.kotlinreactivebilyportfolio.location.data.IPLocation
import fr.bily.kotlinreactivebilyportfolio.location.data.randomIPLocation
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriBuilder
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.net.URI
import java.util.function.Function

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension::class)
internal class IPDataLocationRepositoryTest {
    @Mock
    private lateinit var builder: WebClient.Builder

    @Mock
    private lateinit var webClient: WebClient

    @Mock
    private lateinit var uriSpec: WebClient.RequestHeadersUriSpec<*>


    @Mock
    private lateinit var mockResponse: WebClient.ResponseSpec


    @BeforeEach
    fun setup() {
        `when`(builder.baseUrl(org.mockito.kotlin.any())).thenReturn(builder)
        `when`(builder.build()).thenReturn(webClient)
        `when`(webClient.get()).thenReturn(uriSpec)
        `when`(uriSpec.retrieve()).thenReturn(mockResponse)
    }

    @Test
    fun getCurrentUserLocation() {
        val repository = IPDataLocationRepository(builder, "http://weather-test.ca")
        `when`(mockResponse.bodyToMono(any<Class<IPLocation>>())).thenReturn(Mono.just(randomIPLocation))
        val locationMono = repository.getCurrentUserLocation()
        StepVerifier.create(locationMono.log())
                .expectNext(randomIPLocation)
                .expectComplete()
                .verify()

    }
}