package com.example.retrofit.tutorial_1.retrofit

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 싱글턴
object RetrofitClient{
    //레트로핏 클라이언트 선언

    private var retrofitClient: Retrofit? = null

    //레트로핏 클라이언트 가져오기
    fun getClient(baseUrl:String) : Retrofit?{
        Log.d("RE","getclient called")

        if(retrofitClient == null){

            //레트로핏 빌더를 통해 인스터스 생성
           retrofitClient = Retrofit.Builder()
               .baseUrl(baseUrl)
               .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofitClient
    }
}