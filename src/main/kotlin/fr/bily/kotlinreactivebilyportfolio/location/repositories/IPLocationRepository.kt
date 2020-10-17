package fr.bily.kotlinreactivebilyportfolio.location.repositories

import fr.bily.kotlinreactivebilyportfolio.location.data.IPLocation
import reactor.core.publisher.Mono

interface IPLocationRepository {
    fun getCurrentUserLocation(): Mono<IPLocation>;
}