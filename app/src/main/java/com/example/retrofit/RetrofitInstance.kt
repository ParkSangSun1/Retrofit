package com.example.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//레트로핏 인스턴스 클래스
class RetrofitInstance {


    //companion object를 추가하는 이유는 companion object 개체는 클래스가 처음 로드 될때 초기화 됩니다
    //companion object 객체 내부에 레트로핏 인스턴스를 만들면 클래스 이름을 사용하여 쉽게 사용가능
    companion object{
        //앞에서 한 baseurl과 endurl 설명 참조
        //http를 사용하면 안되고 https를 사용해야함(안드로이드 9.0 파이에서는 https를 사용하도록 강제)
        val BASE_URL = "https://jsonplaceholder.typicode.com/"


        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()


        fun getRetrofitInstance():Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                    .client(client)
                //GSON 변환기 팩토리를 사용하여 JSON을 Kotlin으로 변환합니다
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                //마지막으로 빌드
                .build()
        }
    }
}