package fr.bily.kotlinreactivebilyportfolio.sitemode.services

import fr.bily.kotlinreactivebilyportfolio.location.repositories.IPLocationRepository
import fr.bily.kotlinreactivebilyportfolio.sitemode.data.PortfolioSiteMode
import fr.bily.kotlinreactivebilyportfolio.sitemode.data.SiteMode
import fr.bily.kotlinreactivebilyportfolio.weather.repositories.WeatherForecastRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

val codeMappings = setOf(
        Pair("2", SiteMode.DARK),
        Pair("3", SiteMode.DARK),
        Pair("5", SiteMode.DARK),
        Pair("6", SiteMode.DARK),
        Pair("7", SiteMode.DARK),
        Pair("800", SiteMode.LIGHT),
        Pair("80", SiteMode.BALANCED)
)

@Service
class WeatherSiteModeService(private val weatherRepository: WeatherForecastRepository, private val locationRepository: IPLocationRepository) : SiteModeService {
    override fun computePortfolioSiteMode(): Mono<PortfolioSiteMode> {
        return locationRepository.getCurrentUserLocation().flatMap { location ->
            val lon = location.lon
            val lat = location.lat
            weatherRepository.getPointWeatherForecast(lon, lat)

        }.map { forecast ->
            val condition = forecast.weather.first()
            val codeMapping = codeMappings.find { codeMapping -> condition.id.startsWith(codeMapping.first) }
            if (codeMapping != null) PortfolioSiteMode(codeMapping.second) else PortfolioSiteMode(SiteMode.BALANCED)
        }
    }

}

