package com.example.dan.kadesubmission2.repository

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.dan.kadesubmission2.model.ServiceGenerator
import com.example.dan.kadesubmission2.model.entity.FixtureDetailsFeed
import com.example.dan.kadesubmission2.model.entity.FixtureFeed
import com.example.dan.kadesubmission2.model.entity.TeamLogoFeed
import com.example.dan.kadesubmission2.model.networkLayer.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class FixtureRepository() {
    val TAG = "Fixture Repository"
    companion object { val instance = FixtureRepository }

    private val placeHolderApi : ApiInterface

    //initialize the val placeHolderApi
    init {
        placeHolderApi = ServiceGenerator.createService(ApiInterface::class.java)
    }

    fun fetchFixturesFeed(): MutableLiveData<FixtureFeed>{

        val call = placeHolderApi.getPastEvents()
        val fixturesLiveData : MutableLiveData<FixtureFeed> = MutableLiveData()

        call.enqueue(object: Callback<FixtureFeed> {
            override fun onResponse(call: Call<FixtureFeed>, response: Response<FixtureFeed>) {
                fixturesLiveData.value = response.body()
                Log.d(TAG, fixturesLiveData.value?.fixtures!![0].homeClub)
                println("HASIL REPO"+fixturesLiveData.value?.fixtures!![0].homeClub)

            }

            override fun onFailure(call: Call<FixtureFeed>, t: Throwable) {
                println("Failed to POST Message: ${ t?.message }")
                fixturesLiveData.value = null
            }

        } )
        return fixturesLiveData
    }


    @SuppressLint("SimpleDateFormat")
    fun toSimpleString(date: Date?): String? = with(date ?: Date()) {
        SimpleDateFormat("EEE, dd MM yyy").format(this)
    }

    fun fetchNextFixturesFeed(): MutableLiveData<FixtureFeed>{

        val call = placeHolderApi.getNextEvents()
        val fixturesLiveData : MutableLiveData<FixtureFeed> = MutableLiveData()

        call.enqueue(object: Callback<FixtureFeed> {
            override fun onResponse(call: Call<FixtureFeed>, response: Response<FixtureFeed>) {
                fixturesLiveData.value = response.body()
                Log.d(TAG, fixturesLiveData.value?.fixtures!![0].homeClub)
                println("HASIL REPO"+fixturesLiveData.value?.fixtures!![0].homeClub)

            }

            override fun onFailure(call: Call<FixtureFeed>, t: Throwable) {
                println("Failed to POST Message: ${ t?.message }")
                fixturesLiveData.value = null
            }

        } )
        return fixturesLiveData
    }

    fun fetchTeamLogo(idClub: String): MutableLiveData<TeamLogoFeed>{

        val call = placeHolderApi.getTeamBadge(idClub)
        val teamLogo : MutableLiveData<TeamLogoFeed> = MutableLiveData()

        call.enqueue(object: Callback<TeamLogoFeed> {
            override fun onResponse(call: Call<TeamLogoFeed>, response: Response<TeamLogoFeed>) {
                teamLogo.value = response.body()
                Log.d(TAG, teamLogo.value?.teamLogos!![0].linkClubLogo)
                println("HASIL REPO Logo : "+teamLogo.value?.teamLogos!![0].linkClubLogo)

            }

            override fun onFailure(call: Call<TeamLogoFeed>, t: Throwable) {
                println("Failed to POST Message: ${ t?.message }")
                teamLogo.value = null
            }

        } )
        return teamLogo
    }

    fun fetchFixtureDetails(idEvent: String): MutableLiveData<FixtureDetailsFeed> {
        val call = placeHolderApi.getEventDetails(idEvent)
        val eventDetails : MutableLiveData<FixtureDetailsFeed> = MutableLiveData()

        call.enqueue(object : Callback<FixtureDetailsFeed>{
            override fun onResponse(call: Call<FixtureDetailsFeed>, response: Response<FixtureDetailsFeed>) {
                eventDetails.value = response.body()
//                Log.d(TAG, eventDetails.value.fixtureDetails?.get(0)?.homeGoalKeeper)
                println("HASIL REPO Events : "+eventDetails.value!!.fixtureDetails!![0].homeShots)
                println("HASIL REPO Events : "+eventDetails.value!!.fixtureDetails!![0].homeGoalDetails)
            }

            override fun onFailure(call: Call<FixtureDetailsFeed>, t: Throwable) {
                println("Failed to POST Message: ${ t?.message }")
                eventDetails.value = null
            }
        })

        return eventDetails
    }


}