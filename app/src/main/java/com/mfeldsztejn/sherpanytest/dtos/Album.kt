package com.mfeldsztejn.sherpanytest.dtos

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "albums",
        foreignKeys = [
            ForeignKey(
                    entity = User::class,
                    parentColumns = ["user_id"],
                    childColumns = ["userId"],
                    onDelete = ForeignKey.CASCADE,
                    onUpdate = ForeignKey.CASCADE
            )
        ])
data class Album(@PrimaryKey val id: Int, val title: String, val userId: Int)