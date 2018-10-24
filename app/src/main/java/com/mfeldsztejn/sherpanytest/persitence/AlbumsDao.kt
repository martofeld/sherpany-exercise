package com.mfeldsztejn.sherpanytest.persitence

import androidx.room.Dao
import com.mfeldsztejn.sherpanytest.dtos.Album

@Dao
interface AlbumsDao : BaseDao<Album>