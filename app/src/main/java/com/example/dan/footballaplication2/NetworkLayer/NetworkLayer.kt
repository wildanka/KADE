package com.example.dan.footballaplication2.NetworkLayer

import com.example.dan.footballaplication2.Model.Team
import com.example.dan.footballaplication2.Model.TeamResponse
import rx.Single

class NetworkLayer{
    companion object { val instance = NetworkLayer() }
    private val serviceInterface : ServiceInterface

    init{
        serviceInterface = RClient.createService(ServiceInterface::class.java)
    }

    fun getTeamRx(leagueName : String) : Single<List<Team>> {
        return serviceInterface.getLeagueTeamRx(leagueName);
    }
}