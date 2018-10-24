package com.mfeldsztejn.sherpanytest.persitence

import androidx.room.Dao
import com.mfeldsztejn.sherpanytest.dtos.User

@Dao
interface UsersDao : BaseDao<User>