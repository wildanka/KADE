package com.example.dan.kadesubmission2.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.dan.kadesubmission2.model.entity.FixtureFeed
import com.example.dan.kadesubmission2.model.entity.TeamLogoFeed
import com.example.dan.kadesubmission2.repository.FixtureRepository

class PrevFixturesFragmentViewModel : ViewModel(){
    private lateinit var fixtures : MutableLiveData<FixtureFeed>
    private val repo = FixtureRepository()


    fun getFixtureFeed(): LiveData<FixtureFeed>{
        if (!::fixtures.isInitialized) {
            loadFixtures()
        }
        return fixtures
    }

    fun loadFixtures(){
        fixtures = repo.fetchFixturesFeed()
    }

    fun getFixtures(): LiveData<FixtureFeed>{
        return fixtures
    }
}