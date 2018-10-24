package com.mfeldsztejn.sherpanytest.models

import androidx.room.Embedded
import com.mfeldsztejn.sherpanytest.dtos.Post
import com.mfeldsztejn.sherpanytest.dtos.User

data class PostUserModel(@Embedded val post: Post, @Embedded val user: User)