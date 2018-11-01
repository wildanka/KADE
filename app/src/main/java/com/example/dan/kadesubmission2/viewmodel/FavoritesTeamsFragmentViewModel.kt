package com.example.dan.kadesubmission2.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.dan.kadesubmission2.model.entity.FavoriteTeams
import com.example.dan.kadesubmission2.repository.StorageTeamRepository

class FavoritesTeamsFragmentViewModel : ViewModel(){
//    private lateinit var fixtures : MutableLiveData<FixtureFeed>
    private val repo = StorageTeamRepository()
    private lateinit var favorites : MutableLiveData<List<FavoriteTeams>>

    fun getFavoritesTeams(mContext : Context): MutableLiveData<List<FavoriteTeams>>{
        if (!::favorites.isInitialized) {
            loadFavoritedTeams(mContext)
        }
        return favorites
    }

    private fun loadFavoritedTeams(mContext : Context){
        favorites = repo.fetchFavoritesTeams(mContext)
    }

}