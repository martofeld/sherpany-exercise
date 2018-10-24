package com.mfeldsztejn.sherpanytest.dtos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(@PrimaryKey @ColumnInfo(name = "user_id") val id: Int, val name: String, val username: String,
                val email: String, val phone: String, val website: String,
                val address: Address, val company: Company)

