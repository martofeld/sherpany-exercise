package com.mfeldsztejn.sherpanytest.models

import androidx.room.Embedded
import androidx.room.Relation
import com.mfeldsztejn.sherpanytest.dtos.Album
import com.mfeldsztejn.sherpanytest.dtos.Photo

class AlbumWithPhotos {
    @Embedded
    var album: Album? = null
    @Relation(parentColumn = "id", entityColumn = "albumId")
    var photos: List<Photo> = emptyList()
}