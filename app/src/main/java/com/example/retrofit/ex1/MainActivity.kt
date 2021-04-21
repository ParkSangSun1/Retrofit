package com.example.retrofit.ex1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.retrofit.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    object RetrofitBuilder {
        var api : API

        init {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            api = retrofit.create(API::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        Handler().postDelayed(
                {
                    RetrofitBuilder.api.getUserInfo().enqueue(object : Callback<UserInfo> {
                        override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                            val userInfo = response.body()
                            Log.d("response", "${userInfo?.userId} ${userInfo?.followers} ${userInfo?.following}")
                        }
                        override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                            Log.d("error", t.message.toString())
                        }
                    })
                },
                2500
        )


    }
}