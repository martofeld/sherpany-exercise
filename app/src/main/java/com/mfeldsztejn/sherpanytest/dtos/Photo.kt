package com.mfeldsztejn.sherpanytest.dtos

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "photos",
        foreignKeys = [
            ForeignKey(
                    entity = Album::class,
                    parentColumns = ["id"],
                    childColumns = ["albumId"],
                    onDelete = ForeignKey.CASCADE,
                    onUpdate = ForeignKey.CASCADE
            )
        ])
data class Photo(@PrimaryKey val id: Int, val title: String,
                 val url: String, val thumbnailUrl: String, val albumId: Int)