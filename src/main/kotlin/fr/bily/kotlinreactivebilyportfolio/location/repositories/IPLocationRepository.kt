package fr.bily.kotlinreactivebilyportfolio.location.repositories

import fr.bily.kotlinreactivebilyportfolio.location.data.IPLocation
import reactor.core.publisher.Mono

interface IPLocationRepository {

    /**
     * return the current user location based on the request ip
     */
    fun getCurrentUserLocation(): Mono<IPLocation>
}