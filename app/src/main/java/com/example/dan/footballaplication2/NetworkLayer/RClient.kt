package com.example.dan.footballaplication2.NetworkLayer

import com.example.dan.footballaplication2.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RClient {
//    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://www.thesportsdb.com/"
    private val httpClient = OkHttpClient.Builder()

    //initialize gson
    val gson = GsonBuilder().create()
//    val BASE_URL = "https://api.github.com/"

    //initialize retrofit
    private val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))

    fun <S> createService(serviceClass: Class<S>):S{
        val client = httpClient.build()
        val retrofit = builder.client(client).build()
        return retrofit.create(serviceClass)
    }
}
