package com.example.dan.footballaplication2.NetworkLayer

import com.example.dan.footballaplication2.Model.Team
import com.example.dan.footballaplication2.Model.TeamResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Single


interface ServiceInterface {
    @GET("/api/v1/json/1/search_all_teams.php?l={leagueName}")
    fun getLeague(@Path("leagueName") leagueName: String): Observable<TeamResponse>

    @GET("/api/v1/json/1/search_all_teams.php?l={leagueName}")
    fun getLeagueTeamRx(@Path("leagueName") leagueName: String): Single<List<Team>>
    /*@GET("users/{username}")
    fun getUser(@Path("username") username: String): Observable<Github>*/
}
