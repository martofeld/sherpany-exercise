package com.mfeldsztejn.sherpanytest.persitence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.mfeldsztejn.sherpanytest.dtos.Album
import com.mfeldsztejn.sherpanytest.models.AlbumWithPhotos

@Dao
abstract class AlbumsDao : BaseDao<Album>() {
    @Query("SELECT * FROM albums where id=:id")
    abstract override fun find(id: Int): Album?

    @Query("SELECT * FROM albums where userId=:userId")
    abstract fun albumWithPhotosForUser(userId: Int): LiveData<List<AlbumWithPhotos>>
}