package com.example.retrofit.tutorial_1.retrofit

import android.util.Log
import com.example.retrofit.tutorial_1.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 싱글턴
object RetrofitClient{
    //레트로핏 클라이언트 선언

    private var retrofitClient: Retrofit? = null

    //레트로핏 클라이언트 가져오기
    fun getClient(baseUrl:String) : Retrofit?{
        Log.d(Constants.TAG,"getclient called")


        //로깅 인터셉터 추가

        //okhttp 인스턴트 생성
        val client = OkHttpClient.Builder()
        //로그를 찍기 위해 로깅 인터셉터 추가
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
                Log.d(Constants.TAG, "Retrofitclient - log() called / message: $message")


            }

        })

        //기본 파라미터 추가



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