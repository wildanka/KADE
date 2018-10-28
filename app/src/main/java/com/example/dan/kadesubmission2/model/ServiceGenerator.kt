package com.example.dan.kadesubmission2.model

import android.app.Service
import com.example.dan.kadesubmission2.BuildConfig.BASE_URL
import com.example.dan.kadesubmission2.model.networkLayer.ApiInterface
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.Result.response



object ServiceGenerator{
//    val API_BASE_URL = "https://www.thesportsdb.com/"

    private val httpClient = OkHttpClient.Builder()

    private val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //NOTE: Add this line for RxRetrofit

    private fun iniRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun <T> createServices(service: Class<T>): T {
        return iniRetrofit().create(service)
    }


    fun <S> createService(serviceClass: Class<S>): S {
        return createService(serviceClass, null)
    }

    fun <S> createService(serviceClass: Class<S>, authToken: String?): S {
        //since we don't use authToken for TSDB API
//        if (authToken != null) {
//            addRequestHeaders(authToken)
//        }

        val client = httpClient.build()
        val retrofit = builder.client(client).build()
        return retrofit.create(serviceClass)
    }

    private fun addRequestHeaders(authToken: String?) {
        httpClient.interceptors().add(Interceptor { chain ->
            val original = chain.request()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder().header("Authorization", authToken).method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        })
    }



}