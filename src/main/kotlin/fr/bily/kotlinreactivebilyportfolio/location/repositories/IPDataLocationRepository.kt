package fr.bily.kotlinreactivebilyportfolio.location.repositories

import fr.bily.kotlinreactivebilyportfolio.location.data.IPLocation
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Repository
class IPDataLocationRepository(private val webClientBuilder: WebClient.Builder, @Value("\${location.api.url}") private val apiUrl: String) : IPLocationRepository {
    private val webClient: WebClient = webClientBuilder.baseUrl("${apiUrl}/json").build()
    override fun getCurrentUserLocation(): Mono<IPLocation> {
        return return this.webClient.get().retrieve().bodyToMono(IPLocation::class.java)
    }
}