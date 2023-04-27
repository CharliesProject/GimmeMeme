package com.example.restapp.network

import com.example.restapp.JsonObjectFewMemes
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"
private const val BASE_URL =  "https://meme-api.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MyApiService {
   // @GET("realestate")
   @GET("gimme/10")
 //   suspend fun getPhotos(): List<SinglePhoto>
   suspend fun getPhotos(): JsonObjectFewMemes
}

object MyApi {
    val retrofitService: MyApiService by lazy {
        retrofit.create(MyApiService::class.java)
    }
}
