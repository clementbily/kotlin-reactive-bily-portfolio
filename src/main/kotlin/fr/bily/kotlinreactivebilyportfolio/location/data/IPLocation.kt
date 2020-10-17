package fr.bily.kotlinreactivebilyportfolio.location.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue

data class IPLocation(
        var query: String,
        var status: String,
        var country: String,
        var countryCode: String,
        var region: String,
        var regionName: String,
        var city: String,
        var zip: String,
        var lat: Float,
        var lon: Float,
        var timezone: String,
        var isp: String,
        var org: String,
        @JsonProperty("as")
        var asNumber: String
)