package com.mfeldsztejn.sherpanytest.persitence

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mfeldsztejn.sherpanytest.dtos.Address
import com.mfeldsztejn.sherpanytest.dtos.Company

class Converters {
    @TypeConverter
    fun addressToString(address: Address): String = Gson().toJson(address)

    @TypeConverter
    fun stringToAddress(string: String): Address = Gson().fromJson(string, Address::class.java)

    @TypeConverter
    fun companyToString(company: Company): String = Gson().toJson(company)

    @TypeConverter
    fun stringToCompany(string: String): Company = Gson().fromJson(string, Company::class.java)
}