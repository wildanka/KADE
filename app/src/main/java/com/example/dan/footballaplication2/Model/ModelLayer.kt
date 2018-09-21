package com.example.dan.footballaplication2.Model

import com.example.dan.footballaplication2.NetworkLayer.NetworkLayer
import rx.Single

class ModelLayer{
    companion object {
        val shared = ModelLayer()
    }

    private val networkLayer = NetworkLayer.instance

    //region Rx Network
    fun getLeagueTeamRx(leagueName: String): Single<List<Team>> {
        return networkLayer.getTeamRx(leagueName)
    }
}