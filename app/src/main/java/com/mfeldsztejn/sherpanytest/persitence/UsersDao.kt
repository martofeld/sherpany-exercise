package com.mfeldsztejn.sherpanytest.persitence

import androidx.room.Dao
import androidx.room.Query
import com.mfeldsztejn.sherpanytest.dtos.User

@Dao
abstract class UsersDao : BaseDao<User>() {
    @Query("SELECT * FROM USERS where user_id=:id")
    abstract override fun find(id: Int): User?
}