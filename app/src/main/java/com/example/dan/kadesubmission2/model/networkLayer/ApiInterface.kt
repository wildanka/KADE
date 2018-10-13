package com.example.dan.kadesubmission2.model.networkLayer

import com.example.dan.kadesubmission2.model.entity.FixtureDetailsFeed
import com.example.dan.kadesubmission2.model.entity.FixtureFeed
import com.example.dan.kadesubmission2.model.entity.TeamLogoFeed
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface{
    companion object {
        fun create(){
            val retrofit: Retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://www.thesportsdb.com/")
                    .build()

        }
    }

    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<ResponseBody>

    @GET("api/v1/json/1/eventspastleague.php?id=4328")
    fun getPastEvents(): Call<FixtureFeed>

    @GET("api/v1/json/1/eventsnextleague.php?id=4328")
    fun getNextEvents(): Call<FixtureFeed>

    @GET("api/v1/json/1/lookupevent.php")
    fun getEventDetails(@Query("id") idEvent: String): Call<FixtureDetailsFeed>

    @GET("api/v1/json/1/lookupteam.php")
    fun getTeamBadge(@Query("id") idClub: String): Call<TeamLogoFeed>
}