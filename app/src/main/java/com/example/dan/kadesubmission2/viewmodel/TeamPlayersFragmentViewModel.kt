package com.example.dan.kadesubmission2.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.dan.kadesubmission2.model.entity.TeamPlayers
import com.example.dan.kadesubmission2.repository.TeamRepository

class TeamPlayersFragmentViewModel : ViewModel(){
    private lateinit var listOfPlayers : MutableLiveData<TeamPlayers>
    private val repo = TeamRepository()

    fun getPlayerList(idClub : String): LiveData<TeamPlayers>{
        if(!::listOfPlayers.isInitialized) loadPlayerList(idClub)
        return listOfPlayers
    }

    fun loadPlayerList(idClub: String){
        listOfPlayers = repo.fetchTeamPlayers(idClub)
    }

}