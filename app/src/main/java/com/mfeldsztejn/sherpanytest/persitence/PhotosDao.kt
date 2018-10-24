package com.mfeldsztejn.sherpanytest.persitence

import androidx.room.Dao
import androidx.room.Query
import com.mfeldsztejn.sherpanytest.dtos.Photo

@Dao
abstract class PhotosDao : BaseDao<Photo>() {
    @Query("SELECT * FROM photos where id=:id")
    abstract override fun find(id: Int): Photo?
}