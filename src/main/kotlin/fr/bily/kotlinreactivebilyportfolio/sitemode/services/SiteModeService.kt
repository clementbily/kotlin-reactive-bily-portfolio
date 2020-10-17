package fr.bily.kotlinreactivebilyportfolio.sitemode.services

import fr.bily.kotlinreactivebilyportfolio.sitemode.data.PortfolioSiteMode
import reactor.core.publisher.Mono

interface SiteModeService {
    fun computePortfolioSiteMode(): Mono<PortfolioSiteMode>;
}