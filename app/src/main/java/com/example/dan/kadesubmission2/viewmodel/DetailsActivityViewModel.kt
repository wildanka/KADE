package com.example.dan.kadesubmission2.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.dan.kadesubmission2.model.entity.FixtureDetailsFeed
import com.example.dan.kadesubmission2.model.entity.TeamLogoFeed
import com.example.dan.kadesubmission2.repository.FixtureRepository

class DetailsActivityViewModel : ViewModel(){
    private lateinit var homeClubLogoFeed: MutableLiveData<String>
    private lateinit var awayClubLogoFeed: MutableLiveData<String>
    private lateinit var fixtureDataDetail: MutableLiveData<FixtureDetailsFeed>

    private val repo = FixtureRepository()

    fun getHomeClubLogo(){
//        return homeClubLogoFeed.value!!.teamLogos?.idTeam!!
    }

    fun getHomeClubLogoFeed(idClub: String): LiveData<String>{
        if (!::homeClubLogoFeed.isInitialized) {
            loadHomeClubLogo(idClub)
        }
        return homeClubLogoFeed
    }

    fun getAwayClubLogoFeed(idClub: String): LiveData<String>{
        if (!::awayClubLogoFeed.isInitialized){
            loadAwayClubLogo(idClub)
        }
        return awayClubLogoFeed
    }

    fun getFixtureDetails(idEvent: String): LiveData<FixtureDetailsFeed>{
        if(!::fixtureDataDetail.isInitialized){
            loadFixturesDetail(idEvent)
        }
        return fixtureDataDetail
    }

    fun loadHomeClubLogo(idClub : String) {
        this.homeClubLogoFeed = repo.fetchTeamLogo(idClub)
    }

    fun loadAwayClubLogo(idClub : String) {
        this.awayClubLogoFeed = repo.fetchTeamLogo(idClub)
    }

    fun loadFixturesDetail(idEvent: String) {
        this.fixtureDataDetail = repo.fetchFixtureDetails(idEvent)
    }

}