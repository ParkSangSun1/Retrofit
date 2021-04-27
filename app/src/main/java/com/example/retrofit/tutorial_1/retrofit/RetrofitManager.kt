package com.example.retrofit.tutorial_1.retrofit

import android.util.Log
import com.example.retrofit.tutorial_1.API
import com.example.retrofit.tutorial_1.Constants.TAG
import com.example.retrofit.tutorial_1.RESPONSE_STATE
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create

class RetrofitManager {
    //싱글턴이 적용되도록
    companion object {
        val instance = RetrofitManager()
    }

    //레트로핏 인터페이스 가져오기
    private val iRetrofit: IRetrofit? =
        RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    //사진검색 api 호출
    fun searchPhotos(searchTerm: String?, completion: (RESPONSE_STATE, String) -> Unit) {

        val term = searchTerm.let {
            it
        } ?: ""

        val call = iRetrofit?.searchPhotos(searchTerm = term).let {
            it
        } ?: return

        //call : Call<jsonElement> 이기 때문에 enqueue가 가능
        call.enqueue(object : retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.i(TAG,"retrofitmanager - onresponse called / response: ${response.raw()}")
                completion(RESPONSE_STATE.OKAY, response.body().toString())

            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.i(TAG,"retrofitmanager - onfaiure called / t: $t")
                completion(RESPONSE_STATE.FAIL,t.toString())
            }

        })
    }

}