package com.mfeldsztejn.sherpanytest.persitence

import androidx.room.Insert

interface BaseDao<T> {
    @Insert
    fun insertAll(list: List<T>)
}