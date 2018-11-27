package com.example.dan.kadesubmission2.repository

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.dan.kadesubmission2.model.ServiceGenerator
import com.example.dan.kadesubmission2.model.entity.*
import com.example.dan.kadesubmission2.model.networkLayer.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class FixtureRepository() {
    val TAG = "Fixture Repository"
    companion object { val instance = FixtureRepository }

    private val placeHolderApi : ApiInterface

    //initialize the val placeHolderApi
    init {
        placeHolderApi = ServiceGenerator.createService(ApiInterface::class.java)
    }

    fun fetchFixturesFeed(idLeague : String): MutableLiveData<FixtureFeed>{
        val fixturesLiveData : MutableLiveData<FixtureFeed> = MutableLiveData()
        ServiceGenerator
                .createServices(ApiInterface::class.java)
                .getPastEvents(idLeague)
                .enqueue(object: Callback<FixtureFeed> {
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

    fun fetchSearchFixturesFeed(events : String): MutableLiveData<SearchEventFeeds>{
        val fixturesLiveData : MutableLiveData<SearchEventFeeds> = MutableLiveData()
        val fixtures : MutableLiveData<List<FixtureList>> = MutableLiveData()
        ServiceGenerator
                .createServices(ApiInterface::class.java)
                .getSearchEvents(events)
                .enqueue(object: Callback<SearchEventFeeds> {
                    override fun onResponse(call: Call<SearchEventFeeds>, response: Response<SearchEventFeeds>) {
                        Log.d(TAG,"onResponse BID : Server Response"+response.toString())
                        if (response.body() != null){
                            Log.d(TAG,"onResponse BID : Received Info"+response.body().toString())
                            fixturesLiveData.value = response.body()
                            fixtures.value = fixturesLiveData.value?.fixtures

                            Log.d(TAG, fixtures.value?.get(0)?.homeClub)
//                            println("HASIL REPO"+fixturesLiveData.value?.fixtures!![0].homeClub)
                        }

                    }

                    override fun onFailure(call: Call<SearchEventFeeds>, t: Throwable) {
                        println("Failed to POST Message: ${ t?.message }")
                        fixturesLiveData.value = null
                    }
        } )
        return fixturesLiveData
    }

    fun fetchNextFixturesFeed(idLeague: String): MutableLiveData<FixtureFeed>{

        val call = placeHolderApi.getNextEvents(idLeague)
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

    fun fetchTeamLogo(idClub: String): MutableLiveData<String>{
//        val call = placeHolderApi.getTeamBadge(idClub)
        val teamLogo : MutableLiveData<TeamLogoFeed> = MutableLiveData()
        val urlTeamLogo : MutableLiveData<String> = MutableLiveData()
        ServiceGenerator
                .createServices(ApiInterface::class.java)
                .getTeamBadge(idClub)
                .enqueue(object: Callback<TeamLogoFeed> {
                override fun onResponse(call: Call<TeamLogoFeed>, response: Response<TeamLogoFeed>) {
                    teamLogo.value = response.body()
                    Log.d(TAG, teamLogo.value?.teamLogos!![0].linkClubLogo)
                    urlTeamLogo.value = teamLogo.value?.teamLogos!![0].linkClubLogo
                    println("HASIL REPO Logo : "+teamLogo.value?.teamLogos!![0].linkClubLogo)
                }

                override fun onFailure(call: Call<TeamLogoFeed>, t: Throwable) {
                    println("Failed to POST Message: ${ t?.message }")
                    teamLogo.value = null
                    urlTeamLogo.value = "KOSONG?? Astagtirullah"
                }
        } )
        return urlTeamLogo
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