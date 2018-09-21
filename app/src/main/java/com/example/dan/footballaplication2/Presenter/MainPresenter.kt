package com.example.dan.footballaplication2.Presenter

import com.example.dan.footballaplication2.Model.ModelLayer
import com.example.dan.footballaplication2.Model.Team
import com.example.dan.footballaplication2.Model.TeamResponse
import com.example.dan.footballaplication2.NetworkLayer.RClient
import com.example.dan.footballaplication2.View.MainView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import rx.Single

class MainPresenter(private val view: MainView,
                    /*private val apiRepository: ApiRepository,*/
                    private val gson: Gson) {

    private val modelLayer = ModelLayer.shared //normally injected

    fun getTeamList(league: String?) {
        view.showLoading()
        doAsync {
            /*val data = gson.fromJson(builder.c
                    .doRequest(RClient.getTeams(league)),
                    TeamResponse::class.java
            )*/

            uiThread {
                view.hideLoading()
//                view.showTeamList(data.teams)
            }
        }
    }

    //New Way
    fun getLeagueTeamRx(leagueName : String): Single<List<Team>> {
        return modelLayer.getLeagueTeamRx(leagueName)
    }
}