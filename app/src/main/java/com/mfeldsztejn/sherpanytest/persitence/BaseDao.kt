package com.mfeldsztejn.sherpanytest.persitence

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<T>)
}