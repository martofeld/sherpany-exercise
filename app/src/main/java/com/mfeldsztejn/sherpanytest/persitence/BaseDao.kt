package com.mfeldsztejn.sherpanytest.persitence

import android.util.Log
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.mfeldsztejn.sherpanytest.dtos.Persistable

abstract class BaseDao<T : Persistable> {
    fun insertAll(list: List<T>) {
        list.forEach {
            try {
                val storedValue = find(it.obtainId())
                if (storedValue == null) {
                    insert(it)
                } else {
                    if (storedValue != it) {
                        update(it)
                    }
                }
            } catch (e: Exception) {
                Log.e("TAG", "Failed to insert $it", e)
            }
        }
    }

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract fun insert(t: T)

    @Update
    abstract fun update(t: T)

    @Delete
    abstract fun delete(t: T)

    abstract fun find(id: Int): T?
}