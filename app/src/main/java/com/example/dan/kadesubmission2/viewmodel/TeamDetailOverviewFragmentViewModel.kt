package com.example.dan.kadesubmission2.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.dan.kadesubmission2.repository.TeamRepository

class TeamDetailOverviewFragmentViewModel : ViewModel(){
    private lateinit var overviews : MutableLiveData<String>
    private val repo = TeamRepository()

    fun getOverview(idClub : String): LiveData<String>{
        if (!::overviews.isInitialized){
            loadOverview(idClub)
        }
        return overviews
    }

    fun loadOverview(idClub : String){
        overviews = repo.fetchTeamDetailsOverview(idClub)
    }

}