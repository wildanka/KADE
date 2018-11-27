package com.example.dan.kadesubmission2.model.networkLayer

import com.example.dan.kadesubmission2.model.entity.*
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

    @GET("/api/v1/json/1/searchevents.php")
    fun getSearchEvents(@Query("e") events: String): Call<SearchEventFeeds>

    @GET("/api/v1/json/1/eventspastleague.php")
    fun getPastEvents(@Query("id") idLeague: String): Call<FixtureFeed>

    @GET("/api/v1/json/1/eventsnextleague.php")
    fun getNextEvents(@Query("id") idLeague: String): Call<FixtureFeed>

    @GET("/api/v1/json/1/lookupevent.php")
    fun getEventDetails(@Query("id") idEvent: String): Call<FixtureDetailsFeed>

    @GET("/api/v1/json/1/lookupteam.php")
    fun getTeamBadge(@Query("id") idClub: String): Call<TeamLogoFeed>

    @GET("/api/v1/json/1/searchteams.php")
    fun getSearchTeams(@Query("t") clubName: String): Call<TeamLogoFeed>

    @GET("/api/v1/json/1/lookup_all_teams.php")
    fun getTeamInLeague(@Query("id") idLeague: String): Call<TeamLogoFeed>

    @GET("/api/v1/json/1/lookupteam.php")
    fun getTeamsOverview(@Query("id") idClub: String): Call<TeamDetailsFeed>

    @GET("/api/v1/json/1/lookup_all_players.php")
    fun getTeamPlayers(@Query("id") idClub: String): Call<TeamPlayers>
}