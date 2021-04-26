package com.example.retrofit.tutorial_1.retrofit

import com.example.retrofit.tutorial_1.API
import retrofit2.Retrofit
import retrofit2.create

class RetrofitManager {
    //싱글턴이 적용되도록
    companion object{
        val instance = RetrofitManager()
    }

    //레트로핏 인터페이스 가져오기
    private val iRetrofit : IRetrofit? = RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    //사진검색 api 호출
    fun searchPhotos(searchTerm:String?, completion: (String) -> Unit){

    }

}