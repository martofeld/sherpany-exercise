package com.mfeldsztejn.sherpanytest.persitence

import androidx.room.Dao
import androidx.room.Query
import com.mfeldsztejn.sherpanytest.dtos.Album

@Dao
abstract class AlbumsDao : BaseDao<Album>() {
    @Query("SELECT * FROM albums where id=:id")
    abstract override fun find(id: Int): Album?
}