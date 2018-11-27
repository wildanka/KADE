package com.example.dan.kadesubmission2.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.dan.kadesubmission2.model.entity.FixtureFeed
import com.example.dan.kadesubmission2.model.entity.SearchEventFeeds
import com.example.dan.kadesubmission2.repository.FixtureRepository

class SearchFragmentViewModel : ViewModel(){
    private lateinit var fixtures : MutableLiveData<SearchEventFeeds>
    private val repo = FixtureRepository()

    fun getSearchFixtureFeed(idLeague : String): LiveData<SearchEventFeeds> {
        if (!::fixtures.isInitialized) {
            loadSearchFixtures(idLeague)
        }
        return fixtures
    }

    fun loadSearchFixtures(event : String){
        fixtures = repo.fetchSearchFixturesFeed(event)
    }

}