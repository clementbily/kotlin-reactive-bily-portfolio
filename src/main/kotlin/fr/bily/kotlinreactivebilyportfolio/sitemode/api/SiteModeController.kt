package fr.bily.kotlinreactivebilyportfolio.sitemode.api

import fr.bily.kotlinreactivebilyportfolio.sitemode.data.PortfolioSiteMode
import fr.bily.kotlinreactivebilyportfolio.sitemode.services.SiteModeService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@CrossOrigin
@RequestMapping("site-mode")
class SiteModeController(val siteModeService: SiteModeService) {
    @GetMapping("")
    fun getSite(): Mono<PortfolioSiteMode> {
        return siteModeService.computePortfolioSiteMode()
    }
}