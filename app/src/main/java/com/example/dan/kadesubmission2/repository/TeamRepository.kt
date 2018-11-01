package com.example.dan.kadesubmission2.repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.dan.kadesubmission2.model.ServiceGenerator
import com.example.dan.kadesubmission2.model.entity.*
import com.example.dan.kadesubmission2.model.networkLayer.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamRepository() {
    val TAG = "Team Repository"
    companion object { val instance = TeamRepository }

    private val placeHolderApi : ApiInterface

    //initialize the val placeHolderApi
    init {
        placeHolderApi = ServiceGenerator.createService(ApiInterface::class.java)
    }

    fun fetchLeagueTeams(idLeague: String): MutableLiveData<TeamLogoFeed> {
        val call = placeHolderApi.getTeamInLeague(idLeague)
        val teamsInLeague : MutableLiveData<TeamLogoFeed> = MutableLiveData()

        call.enqueue(object : Callback<TeamLogoFeed> {
            override fun onResponse(call: Call<TeamLogoFeed>, response: Response<TeamLogoFeed>) {
                teamsInLeague.value = response.body()
//                Log.d(TAG, eventDetails.value.fixtureDetails?.get(0)?.homeGoalKeeper)
                println("HASIL REPO Teams : "+teamsInLeague.value!!.teamLogos!![0].idTeam)
                println("HASIL REPO Teams : "+teamsInLeague.value!!.teamLogos!![0].teamName)
                println("HASIL REPO Teams : "+teamsInLeague.value!!.teamLogos!![0].linkClubLogo)
            }

            override fun onFailure(call: Call<TeamLogoFeed>, t: Throwable) {
                println("Failed to POST Message: ${ t?.message }")
                teamsInLeague.value = null
            }
        })

        return teamsInLeague
    }

    fun fetchTeamDetailsOverview(idClub: String): MutableLiveData<String> {
        val call = placeHolderApi.getTeamsOverview(idClub)
        val teamsInLeague : MutableLiveData<TeamDetailsFeed> = MutableLiveData()
        val teamsOverview : MutableLiveData<String> = MutableLiveData()

        call.enqueue(object : Callback<TeamDetailsFeed> {
            override fun onResponse(call: Call<TeamDetailsFeed>, response: Response<TeamDetailsFeed>) {
                teamsInLeague.value = response.body()
                teamsOverview.value = teamsInLeague.value!!.teamOverviews!![0].strDescriptionEN
                println("HASIL REPO Overviews : "+teamsInLeague.value!!.teamOverviews!![0].strDescriptionEN)
            }

            override fun onFailure(call: Call<TeamDetailsFeed>, t: Throwable) {
                println("Failed to POST Message: ${ t?.message }")
                teamsOverview.value = null
            }
        })

        return teamsOverview
    }

    fun fetchTeamPlayers(idClub: String): MutableLiveData<TeamPlayers> {
        val call = placeHolderApi.getTeamPlayers(idClub)
//        val teamsInLeague : MutableLiveData<TeamDetailsFeed> = MutableLiveData()
        val teamPlayers : MutableLiveData<TeamPlayers> = MutableLiveData()

        call.enqueue(object : Callback<TeamPlayers> {
            override fun onResponse(call: Call<TeamPlayers>, response: Response<TeamPlayers>) {
                teamPlayers.value = response.body()
//                teamsOverview.value = teamsInLeague.value!!.teamOverviews!![0].strDescriptionEN
                println("HASIL REPO Overviews : "+teamPlayers.value!!.playerList!![0].strPlayerName)
            }

            override fun onFailure(call: Call<TeamPlayers>, t: Throwable) {
                println("Failed to POST Message: ${ t?.message }")
                teamPlayers.value = null
            }
        })

        return teamPlayers
    }

}