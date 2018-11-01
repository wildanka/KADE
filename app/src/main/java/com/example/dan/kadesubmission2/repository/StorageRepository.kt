package com.example.dan.kadesubmission2.repository

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.example.dan.kadesubmission2.model.entity.Favorite
import com.example.dan.kadesubmission2.model.localStorage.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class StorageRepository{

    val TAG = "StorageRepository"
    companion object { val instance = StorageRepository }

    fun fetchFavoritesFixture(mContext: Context): MutableLiveData<List<Favorite>> {
        val favoritesReturn : MutableLiveData<List<Favorite>> = MutableLiveData()
        val favorites : MutableList<Favorite> = mutableListOf()
        try {
            mContext!!.database.use {
                val result = select(Favorite.TABLE_FAVORITE_EVENTS)
                val favorite = result.parseList(classParser<Favorite>())
                favorites.addAll(favorite)
                favoritesReturn.value = favorites
            }
        }catch (e: SQLiteConstraintException){
            println("Error while inserting data to FIXTURES database: ${ e?.message }")
            Log.getStackTraceString(e)
            return favoritesReturn
        }

        return favoritesReturn
    }

}