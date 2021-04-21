package com.example.retrofit.ex1

import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET("users/ParkSangSun1/")
    fun getUserInfo(): Call<UserInfo>
}