package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val retService = RetrofitInstance
                .getRetrofitInstance()
                .create(AlbumService::class.java)
        val responseLiveData : LiveData<Response<Albums>> = liveData {
            //인터페이스 사용하여 응답 개체를 가져옴
            val response = retService.getAlbums()
            //방출
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val albumsList = it.body()?.listIterator()
            if(albumsList!= null){
                while (albumsList.hasNext()){
                    val albumsItem = albumsList.next()
                    Log.i("MYTAG",albumsItem.title)
                    val result = " "+"Album Title : ${albumsItem.title}"+"\n"+
                                " "+"Album id : ${albumsItem.id}"+"\n"+
                                " "+"User id : ${albumsItem.userId}"+"\n\n\n"
                    binding.textView.append(result)
                }
            }
        })

    }
}