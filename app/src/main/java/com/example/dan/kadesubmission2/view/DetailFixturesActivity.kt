package com.example.dan.kadesubmission2.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.model.entity.FixtureDetailsFeed
import com.example.dan.kadesubmission2.model.entity.TeamLogoFeed
import com.example.dan.kadesubmission2.viewmodel.DetailsActivityViewModel
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_detail_fixtures.*
import kotlinx.android.synthetic.main.content_detail_fixtures.*

class DetailFixturesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_fixtures)

        //binding data
        setSupportActionBar(toolbar)
        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val idHome : String = intent.getStringExtra("ID_CLUB_HOME")
        val idAway : String = intent.getStringExtra("ID_CLUB_AWAY")
        val idEvent : String = intent.getStringExtra("ID_EVENT")
        val viewModel = ViewModelProviders.of(this).get(DetailsActivityViewModel::class.java)

        observeBothTeamLogoFeed(viewModel,idHome,idAway)
        observeEventDetails(viewModel, idEvent)

    }

    private fun observeBothTeamLogoFeed(viewModel: DetailsActivityViewModel, idHome : String, idAway: String) {
        progressBarDetail.visibility = View.VISIBLE
        viewModel.getHomeClubLogoFeed(idHome).observe(this, Observer<TeamLogoFeed>{teamLogo ->
            if (teamLogo != null) {
                Picasso.get().load(teamLogo.teamLogos!![0].linkClubLogo).into(iv_home_team)
            }
        })
        viewModel.getAwayClubLogoFeed(idAway).observe(this, Observer<TeamLogoFeed>{teamLogo ->
            if (teamLogo != null) {
                Picasso.get().load(teamLogo.teamLogos!![0].linkClubLogo).into(iv_away_team)
            }
        })
    }

    private fun observeEventDetails(viewModel: DetailsActivityViewModel, idEvent : String){
        viewModel.getFixtureDetails(idEvent).observe(this, Observer<FixtureDetailsFeed>{eventDetails ->
            if (eventDetails != null) {
                println("EVENT DETAILS : "+ eventDetails.fixtureDetails!![0].strHomeTeam)
                println("EVENT DETAILS : "+ eventDetails.fixtureDetails!![0].strAwayTeam)
                println("EVENT DETAILS : "+ validateData(eventDetails.fixtureDetails!![0].homeGoalDetails))
                println("EVENT DETAILS SHOTS: "+ validateData(eventDetails.fixtureDetails!![0].homeShots))
//                binding.tv.text = eventDetails.fixtureDetails!![0].dateEvent
                tv_home_club_detail.text = validateData(eventDetails.fixtureDetails!![0].strHomeTeam)
                tv_away_club_detail.text = validateData(eventDetails.fixtureDetails!![0].strAwayTeam)
                tv_home_score_detail.text = validateData(eventDetails.fixtureDetails!![0].homeScoreDetail)
                tv_away_score_detail.text = validateData(eventDetails.fixtureDetails!![0].awayScoreDetail)
                tv_home_goals.text = validateData(eventDetails.fixtureDetails!![0].homeGoalDetails)
                tv_away_goals.text = validateData(eventDetails.fixtureDetails!![0].awayGoalDetails)
                tv_home_shot.text = validateData(eventDetails.fixtureDetails!![0].homeShots)
                tv_away_shot.text = validateData(eventDetails.fixtureDetails!![0].awayShots)
                tv_home_goalkeeper.text = validateData(eventDetails.fixtureDetails!![0].homeGoalKeeper)
                tv_away_goalkeeper.text = validateData(eventDetails.fixtureDetails!![0].awayGoalKeeper)
                tv_home_defense.text = validateData(eventDetails.fixtureDetails!![0].homeDefense)
                tv_away_defense.text = validateData(eventDetails.fixtureDetails!![0].awayDefense)
                tv_home_midfield.text = validateData(eventDetails.fixtureDetails!![0].homeMidfield)
                tv_away_midfield.text = validateData(eventDetails.fixtureDetails!![0].awayMidfield)
                tv_home_forward.text = validateData(eventDetails.fixtureDetails!![0].homeForward)
                tv_away_forward.text = validateData(eventDetails.fixtureDetails!![0].awayForward)
                tv_home_subs.text = validateData(eventDetails.fixtureDetails!![0].homeSubs)
                tv_away_subs.text = validateData(eventDetails.fixtureDetails!![0].awaySubs)
                tv_match_time_detail.text = validateData(eventDetails.fixtureDetails!![0].dateEvent)
            }
        })
        progressBarDetail.visibility = View.GONE
    }

    private fun addNewLine(wordList: String): String {
        var newString = wordList.replace(";", ";\n")
        newString = " $newString"
        return newString
    }

    private fun validateData(data: String?): String {
        val dataReturn = ""
        return if (data == null) {
            dataReturn
        } else addNewLine(data)

    }
}