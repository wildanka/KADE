package com.example.dan.kadesubmission2.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.dan.kadesubmission2.model.entity.TeamLogo
import com.example.dan.kadesubmission2.model.entity.TeamLogoFeed
import com.example.dan.kadesubmission2.repository.TeamRepository

class TeamsFragmentViewModel: ViewModel(){
    private lateinit var teams : MutableLiveData<TeamLogoFeed>

    private val repo = TeamRepository()

    fun getTeams(idLeague: String): LiveData<TeamLogoFeed>{
        println("terpelatuk")
        if(!::teams.isInitialized) loadTeams(idLeague)
        return teams
    }

    fun loadTeams(idLeague : String){
        println("viewmodel load from repo terpelatuk")
        teams = repo.fetchLeagueTeams(idLeague)
    }


}