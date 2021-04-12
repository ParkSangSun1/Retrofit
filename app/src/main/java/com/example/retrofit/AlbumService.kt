package com.example.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface AlbumService {

    //레트로핏 인스턴스 class를 만들때는 기본url을 추가하고
    // 이 URL은 url 끝부분만 넣는다
    //예 ) http://jsonplaceholder.typicode.com/albums
    // http://jsonplaceholder.typicode.com (앞부분)
    // /albums (끝부분)
    @GET("/albums")

    //코루틴을 사용할꺼기 때문에 suspend fun 사용
    //retrofit은 항상 Refrofit response객체로 결과를 제공,
    // 때문에 반환값을 Response<Albums>
    suspend fun getAlbums() : Response<Albums>
}