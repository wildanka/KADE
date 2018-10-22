package com.example.dan.kadesubmission2.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.dan.kadesubmission2.model.entity.Favorite
import com.example.dan.kadesubmission2.repository.StorageRepository

class FavoritesFragmentViewModel : ViewModel(){
//    private lateinit var fixtures : MutableLiveData<FixtureFeed>
    private val repo = StorageRepository()
    private lateinit var favorites : MutableLiveData<List<Favorite>>

    fun getFavoritesFixture(mContext : Context): MutableLiveData<List<Favorite>>{
        if (!::favorites.isInitialized) {
            loadFixtures(mContext)
        }
        return favorites
    }

    private fun loadFixtures(mContext : Context){
        favorites = repo.fetchFavoritesFixture(mContext)
    }

}