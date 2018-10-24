package com.mfeldsztejn.sherpanytest.dtos

data class User(val id: Int, val name: String, val username: String,
                val email: String, val phone: String, val website: String,
                val address: Address, val company: Company)

