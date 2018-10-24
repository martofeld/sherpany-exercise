package com.mfeldsztejn.sherpanytest.persitence

import androidx.room.Dao
import com.mfeldsztejn.sherpanytest.dtos.Photo

@Dao
interface PhotosDao : BaseDao<Photo>