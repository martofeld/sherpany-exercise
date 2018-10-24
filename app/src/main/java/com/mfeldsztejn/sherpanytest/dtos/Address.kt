package com.mfeldsztejn.sherpanytest.dtos

data class Address(val street: String, val suite: String, val city: String,
                   val zipcode: String, val geo: LatLng)