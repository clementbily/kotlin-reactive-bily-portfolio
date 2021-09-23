package fr.bily.kotlinreactivebilyportfolio.sitemode.services

import fr.bily.kotlinreactivebilyportfolio.location.data.randomIPLocation
import fr.bily.kotlinreactivebilyportfolio.location.repositories.IPLocationRepository
import fr.bily.kotlinreactivebilyportfolio.sitemode.data.PortfolioSiteMode
import fr.bily.kotlinreactivebilyportfolio.sitemode.data.SiteMode
import fr.bily.kotlinreactivebilyportfolio.weather.data.defaultClearWeatherForecast
import fr.bily.kotlinreactivebilyportfolio.weather.repositories.WeatherForecastRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class WeatherSiteModeServiceTest {

    @Mock
    private lateinit var ipLocationRepositoryMock: IPLocationRepository

    @Mock
    private lateinit var forecastRepositoryMock: WeatherForecastRepository

    @BeforeAll
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun shouldReturnLightModeForDefaultClearWeather() {
        Mockito.`when`(ipLocationRepositoryMock.getCurrentUserLocation()).thenReturn(Mono.just(randomIPLocation))
        Mockito.`when`(forecastRepositoryMock.getPointWeatherForecast(Mockito.anyFloat(), Mockito.anyFloat())).thenReturn(Mono.just(defaultClearWeatherForecast))
        val service = WeatherSiteModeService(forecastRepositoryMock, ipLocationRepositoryMock)
        val siteModeMono = service.computePortfolioSiteMode() // act
        StepVerifier.create(siteModeMono.log())
                .expectNext(PortfolioSiteMode(SiteMode.LIGHT))
                .expectComplete()
                .verify()
        // we assert service call after mono emited value because mono acts as an asynchronous unit
        Mockito.verify(ipLocationRepositoryMock).getCurrentUserLocation();
        Mockito.verify(forecastRepositoryMock)
                .getPointWeatherForecast(randomIPLocation.lon, randomIPLocation.lat)

    }
}