package com.example.dan.kadesubmission2.repository

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.example.dan.kadesubmission2.model.entity.FavoriteTeams
import com.example.dan.kadesubmission2.model.localStorage.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class StorageTeamRepository{

    val TAG = "StorageTeamRepository"
    companion object { val instance = StorageTeamRepository }

    fun fetchFavoritesTeams(mContext: Context): MutableLiveData<List<FavoriteTeams>> {
        val favoritesTeamsReturn : MutableLiveData<List<FavoriteTeams>> = MutableLiveData()
        val favoriteTeams : MutableList<FavoriteTeams> = mutableListOf()
        try {
            mContext!!.database.use {
                val result = select(FavoriteTeams.TABLE_FAVORITE_TEAMS)
                val favorite = result.parseList(classParser<FavoriteTeams>())
                favoriteTeams.addAll(favorite)
                favoritesTeamsReturn.value = favoriteTeams
            }
        }catch (e: SQLiteConstraintException){
            println("Error while inserting data to TEAMS database: ${ e?.message }")
            Log.getStackTraceString(e)
//            snackbar(swipeRefresh, e.localizedMessage).show()
            return favoritesTeamsReturn
        }

        return favoritesTeamsReturn
    }
}
