package com.example.retrofit.tutorial_1.retrofit

import com.example.retrofit.tutorial_1.API
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofit {

    //base Url
    //https://www.unsplash.com/

    //https://www.unsplash.com/search/photos/?query=""

    @GET(API.SEARCH_PHOTO)
    //                                                               반환값
    fun searchPhotos(@Query("query") searchTerm: String) : Call<JsonElement>

    @GET(API.SEARCH_USERS)
    //             (@Query("value") 매개변수 이름)
    fun searchUsers(@Query("query") searchTerm: String) : Call<JsonElement>
}