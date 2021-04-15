package fr.bily.kotlinreactivebilyportfolio.location.repositories

import fr.bily.kotlinreactivebilyportfolio.location.data.IPLocation
import fr.bily.kotlinreactivebilyportfolio.location.data.randomIPLocation
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class IPDataLocationRepositoryTest {

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
        mockResponse = mock(ClientResponse::class.java)
        given(this.exchangeFunction.exchange(this.captor.capture())).willReturn(Mono.just(mockResponse))
        this.builder = WebClient.builder().baseUrl("/base").exchangeFunction(this.exchangeFunction)
    }

    @Test
    fun getCurrentUserLocation() {
        val repository = IPDataLocationRepository(this.builder, "http://weather-test.ca")
        given(mockResponse.bodyToMono(any<Class<IPLocation>>())).willReturn(Mono.just(randomIPLocation))
        val locationMono = repository.getCurrentUserLocation()
        StepVerifier.create(locationMono.log())
                .expectNext(randomIPLocation)
                .expectComplete()
                .verify()

    }
}