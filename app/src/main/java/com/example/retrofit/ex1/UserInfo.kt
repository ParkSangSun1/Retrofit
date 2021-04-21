package com.example.retrofit.ex1

import com.google.gson.annotations.SerializedName

data class UserInfo(
    val userId: String,
    val followers: Int,
    val following: Int
)