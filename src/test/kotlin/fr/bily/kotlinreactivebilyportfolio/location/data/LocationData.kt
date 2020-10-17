package fr.bily.kotlinreactivebilyportfolio.location.data

import java.util.*

val randomIPLocation = IPLocation(timezone= UUID.randomUUID().toString(),
asNumber= UUID.randomUUID().toString(),
city= UUID.randomUUID().toString(),
country= UUID.randomUUID().toString(),
countryCode= UUID.randomUUID().toString(),
isp= UUID.randomUUID().toString(),
lon= Math.random().toFloat(),
lat = Math.random().toFloat(),
org= UUID.randomUUID().toString(),
query= UUID.randomUUID().toString(),
region= UUID.randomUUID().toString(),
regionName= UUID.randomUUID().toString(),
status= UUID.randomUUID().toString(),
        zip = UUID.randomUUID().toString()
);
