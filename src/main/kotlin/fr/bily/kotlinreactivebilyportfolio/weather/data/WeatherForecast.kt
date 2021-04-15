package fr.bily.kotlinreactivebilyportfolio.weather.data

data class WeatherForecast(var weather: Array<Weather>) {
    class Weather(var id: String, var main: String, var description: String, var icon: String)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WeatherForecast

        if (!weather.contentEquals(other.weather)) return false

        return true
    }

    override fun hashCode(): Int {
        return weather.contentHashCode()
    }
}