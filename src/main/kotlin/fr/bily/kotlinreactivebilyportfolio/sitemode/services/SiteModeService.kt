package fr.bily.kotlinreactivebilyportfolio.sitemode.services

import fr.bily.kotlinreactivebilyportfolio.sitemode.data.PortfolioSiteMode
import reactor.core.publisher.Mono

interface SiteModeService {
    /**
     * compute and return the site mode for the server
     */
    fun computePortfolioSiteMode(): Mono<PortfolioSiteMode>
}