package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private lateinit var retService : AlbumService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        retService = RetrofitInstance
                .getRetrofitInstance()
                .create(AlbumService::class.java)
//        getRequesWithQueryParameters()
//        getRequesWithPathParameters()
        uploadAlbum()


        //path parameter example
        val pathResponse : LiveData<Response<AlbumsItem>> = liveData {
            val response = retService.getAlbum(3)
            emit(response)
        }
        pathResponse.observe(this, Observer {
            val title = it.body()?.title
            Toast.makeText(applicationContext, title, Toast.LENGTH_SHORT).show()
            Log.i("ME","pathResponse 실행 ${title}")

        })



        val responseLiveData : LiveData<Response<Albums>> = liveData {
            //인터페이스 사용하여 응답 개체를 가져옴
            val response = retService.getSortedAlbums(4)
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


    private fun getRequesWithQueryParameters(){
        val responseLiveData : LiveData<Response<Albums>> = liveData {
            //인터페이스 사용하여 응답 개체를 가져옴
            val response = retService.getSortedAlbums(4)
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

    private fun getRequesWithPathParameters(){
        //path parameter example
        val pathResponse : LiveData<Response<AlbumsItem>> = liveData {
            val response = retService.getAlbum(3)
            emit(response)
        }
        pathResponse.observe(this, Observer {
            val title = it.body()?.title
            Toast.makeText(applicationContext, title, Toast.LENGTH_SHORT).show()
            Log.i("ME","pathResponse 실행 ${title}")

        })

    }

    private fun uploadAlbum(){
        val album = AlbumsItem(0, "My title",3)

        val postResonse : LiveData<Response<AlbumsItem>> = liveData {
            val response = retService.uploadAlbum(album)
            emit(response)
        }
        postResonse.observe(this, Observer {
            val receivedAlbumsItem = it.body()
            val result = " "+"Album Title : ${receivedAlbumsItem?.title}"+"\n"+
                    " "+"Album id : ${receivedAlbumsItem?.id}"+"\n"+
                    " "+"User id : ${receivedAlbumsItem?.userId}"+"\n\n\n"
            binding.textView.text = result
        })
    }
}