package com.example.dan.kadesubmission2.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.R.drawable.ic_add_to_favorites
import com.example.dan.kadesubmission2.R.drawable.ic_added_to_favorites
import com.example.dan.kadesubmission2.model.entity.Favorite
import com.example.dan.kadesubmission2.model.entity.FixtureDetailsFeed
import com.example.dan.kadesubmission2.model.localStorage.database
import com.example.dan.kadesubmission2.repository.StorageRepository
import com.example.dan.kadesubmission2.util.DateTimeConverter
import com.example.dan.kadesubmission2.viewmodel.DetailsActivityViewModel
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_detail_fixtures.*
import kotlinx.android.synthetic.main.content_detail_fixtures.*
import kotlinx.android.synthetic.main.item_list_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.text.SimpleDateFormat
import java.util.*

class DetailFixturesActivity : AppCompatActivity() {
    private val TAG = "DetailFixturesActivity"
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var idEvent: String
    private lateinit var idHome: String
    private lateinit var idAway: String
    private lateinit var homeClub: String
    private lateinit var awayClub: String
    private lateinit var homeClubScore: String
    private lateinit var awayClubScore: String
    private lateinit var date: String
    private lateinit var time: String

    private lateinit var repo: StorageRepository
    private lateinit var id: String
    private var fixtureDetailsFeed: MutableLiveData<FixtureDetailsFeed> = MutableLiveData()


    override fun onCreate(savedInstanceState: Bundle?) {
        idHome = intent.getStringExtra("ID_CLUB_HOME")
        idAway = intent.getStringExtra("ID_CLUB_AWAY")
        idEvent = intent.getStringExtra("ID_EVENT")
        checkFavoriteState()
        setFavorite()
        println("Favorit : $isFavorite")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_fixtures)

        val viewModel = ViewModelProviders.of(this).get(DetailsActivityViewModel::class.java)

        observeBothTeamLogoFeed(viewModel, idHome, idAway)
        observeEventDetails(viewModel, idEvent)

        //binding data
        setSupportActionBar(toolbar)
        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorites, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorites -> {
                if (::idEvent.isInitialized || ::idHome.isInitialized || ::idAway.isInitialized || ::homeClub.isInitialized || ::awayClub.isInitialized || ::homeClubScore.isInitialized || ::awayClubScore.isInitialized || ::date.isInitialized) {
                    if (isFavorite) removeFromFavorite() else addToFavorite()
                    isFavorite = !isFavorite
                    setFavorite()
                } else {
                    Toast.makeText(this, "Still Retrieving Data from Network, Please Try Again", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE_EVENTS,
                        Favorite.ID_EVENT to idEvent,
                        Favorite.HOME_ID to idHome,
                        Favorite.AWAY_ID to idAway,
                        Favorite.HOME_TEAM to homeClub,
                        Favorite.AWAY_TEAM to awayClub,
                        Favorite.HOME_SCORE to homeClubScore,
                        Favorite.AWAY_SCORE to awayClubScore,
                        Favorite.EVENT_DATE to date,
                        Favorite.EVENT_TIME to time)
            }
//            println("BERHASIL INSERT HOREE")
            Snackbar.make(
                    detail_root_layout,
                    "Match Added to Favorites",
                    Snackbar.LENGTH_LONG
            ).setAction(
                    "UNDO"
            ) {
                removeFromFavorite()
                menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
            }.show()
        } catch (e: SQLiteConstraintException) {
            println("Error while inserting data to database: ${e?.message}")
            Log.getStackTraceString(e)
            Snackbar.make(detail_root_layout, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE_EVENTS, "(ID_EVENT = {id})", "id" to idEvent)
            }
            println("BERHASIL DELETE HOREE")
            val snackbar = Snackbar.make(
                    detail_root_layout,
                    "Match Removed from Favorites",
                    Snackbar.LENGTH_LONG
            ).setAction(
                    "UNDO"
            ) {
                addToFavorite()
                menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
            }.show()

        } catch (e: SQLiteConstraintException) {
            println("Error while Deleting data from database: ${e?.message}")
            Log.getStackTraceString(e)
        }
    }

    private fun checkFavoriteState() {
        try {
            database.use {
                val result = select(Favorite.TABLE_FAVORITE_EVENTS)
                        .whereArgs("(ID_EVENT = {id})",
                                "id" to idEvent)
                val favorite = result.parseList(classParser<Favorite>())
                if (!favorite.isEmpty()) isFavorite = true
            }
        } catch (e: SQLiteConstraintException) {
            println("Error Getting data from database: ${e?.message}")
        }
    }

    private fun observeBothTeamLogoFeed(viewModel: DetailsActivityViewModel, idHome: String, idAway: String) {
        progressBarDetail.visibility = View.VISIBLE
        viewModel.getHomeClubLogoFeed(idHome).observe(this, Observer<String> { teamLogo ->
            if (teamLogo != null) {
                Picasso.get().load(teamLogo).into(iv_home_team)
            }
        })
        viewModel.getAwayClubLogoFeed(idAway).observe(this, Observer<String> { teamLogo ->
            if (teamLogo != null) {
                Picasso.get().load(teamLogo).into(iv_away_team)
            }
        })
    }

    private fun observeEventDetails(viewModel: DetailsActivityViewModel, idEvent: String) {
        viewModel.getFixtureDetails(idEvent).observe(this, Observer<FixtureDetailsFeed> { eventDetails ->

            if (eventDetails != null) {
                this.homeClub = eventDetails.fixtureDetails?.get(0)?.strHomeTeam.toString()
                this.awayClub = eventDetails.fixtureDetails?.get(0)?.strAwayTeam.toString()
                this.homeClubScore = eventDetails.fixtureDetails?.get(0)?.homeScoreDetail.toString()
                this.awayClubScore = eventDetails.fixtureDetails?.get(0)?.awayScoreDetail.toString()

                println("EVENT DETAILS : " + eventDetails.fixtureDetails?.get(0)?.strHomeTeam)
                println("EVENT DETAILS : " + eventDetails.fixtureDetails?.get(0)?.strAwayTeam)
                println("EVENT DETAILS : " + validateData(eventDetails.fixtureDetails?.get(0)?.homeGoalDetails))
                println("EVENT DETAILS SHOTS: " + validateData(eventDetails.fixtureDetails?.get(0)?.homeShots))
//                binding.tv.text = eventDetails.fixtureDetails!![0].dateEvent
                tv_home_club_detail.text = validateData(eventDetails.fixtureDetails?.get(0)?.strHomeTeam)
                tv_away_club_detail.text = validateData(eventDetails.fixtureDetails?.get(0)?.strAwayTeam)
                tv_home_score_detail.text = validateData(eventDetails.fixtureDetails?.get(0)?.homeScoreDetail)
                tv_away_score_detail.text = validateData(eventDetails.fixtureDetails?.get(0)?.awayScoreDetail)
                tv_home_goals.text = validateData(eventDetails.fixtureDetails?.get(0)?.homeGoalDetails)
                tv_away_goals.text = validateData(eventDetails.fixtureDetails?.get(0)?.awayGoalDetails)
                tv_home_shot.text = validateData(eventDetails.fixtureDetails?.get(0)?.homeShots)
                tv_away_shot.text = validateData(eventDetails.fixtureDetails?.get(0)?.awayShots)
                tv_home_goalkeeper.text = validateData(eventDetails.fixtureDetails?.get(0)?.homeGoalKeeper)
                tv_away_goalkeeper.text = validateData(eventDetails.fixtureDetails?.get(0)?.awayGoalKeeper)
                tv_home_defense.text = validateData(eventDetails.fixtureDetails?.get(0)?.homeDefense)
                tv_away_defense.text = validateData(eventDetails.fixtureDetails?.get(0)?.awayDefense)
                tv_home_midfield.text = validateData(eventDetails.fixtureDetails?.get(0)?.homeMidfield)
                tv_away_midfield.text = validateData(eventDetails.fixtureDetails?.get(0)?.awayMidfield)
                tv_home_forward.text = validateData(eventDetails.fixtureDetails?.get(0)?.homeForward)
                tv_away_forward.text = validateData(eventDetails.fixtureDetails?.get(0)?.awayForward)
                tv_home_subs.text = validateData(eventDetails.fixtureDetails?.get(0)?.homeSubs)
                tv_away_subs.text = validateData(eventDetails.fixtureDetails?.get(0)?.awaySubs)

                //1. initialize the eventTime & eventDate to be formatted
                val eventTime: String? = if (eventDetails.fixtureDetails?.get(0)?.strTime == null) { "15:00:00+00:00"}else{eventDetails.fixtureDetails?.get(0)?.strTime}
                var eventDate: String? = null


                // 2. create eventTime after we create the eventDate from this process :
                // 2. create the eventDate from this process :
                // checking whether dateEvent or strDate is null or even both are null?
                if ( eventDetails.fixtureDetails?.get(0)?.dateEvent.isNullOrEmpty() && eventDetails.fixtureDetails?.get(0)?.strDate.isNullOrEmpty()){ // a. if empty then no official date yet
                    eventDate = "03/11/18" //create dummy date
                }else{// b. if one of the variable is not empty

                    if (eventDetails.fixtureDetails?.get(0)?.strDate != null){ //b1. if strDate is exists, then use strDate
                        eventDate = eventDetails.fixtureDetails?.get(0)?.strDate
                    }else { //b2. if strDate is not exists but dateEvent is exists then use dateEvent

                        if (eventDetails.fixtureDetails?.get(0)?.dateEvent != null) { //b2.1 if dateEventData is Exists then use it
                            // to use dateEvent,
                            // First, we need to convert it
                            val strYear = eventDetails.fixtureDetails?.get(0)?.dateEvent?.substring(2, 4)
                            val strM = eventDetails.fixtureDetails?.get(0)?.dateEvent!!.substring(5, 7)
                            val strDay = eventDetails.fixtureDetails?.get(0)?.dateEvent!!.substring(8, 10)
                            println("dateEvent to strDateResult $strDay/$strM/$strYear")
                            //Second, after converting the dateEvent format to the same format as strDate. Update the eventDate
                            eventDate = "$strDay/$strM/$strYear"
                        }
                    }
                }
                //all of the resulted eventDate (from strDate or from converted dateEvent will be used here)
                val strDateResult = DateTimeConverter.toGMTFormat(eventDate, eventTime)
                println("$strDateResult which are from \n " +
                        "month :  ${strDateResult?.month.toString()}  \n" +
                        "year : ${strDateResult?.year.toString()} ")

                //create a calendar instance
                val cal: Calendar = Calendar.getInstance()
                cal.time = strDateResult //and make sure to set the time before use the calendar!

                //show the final result
                if (eventDetails.fixtureDetails?.get(0)?.strDate == null  && eventDetails.fixtureDetails?.get(0)?.dateEvent == null ) { //check the date data, if strDate and dateEvent is null, then the date data is just a dummy
                    this.date = "No Official Date Yet"
                }else{//if date data is real then show it
                    println("CALENDAR MONTH ${cal.get(Calendar.MONTH)} | ${DateTimeConverter.toSimpleString(strDateResult)}")
                    this.date = DateTimeConverter.toSimpleString(strDateResult).toString()
                }

                //Show the Time Data
                if (eventDetails.fixtureDetails?.get(0)?.strTime == null) { //because the data is only dummy data, then don't show it to users
                    this.date = "No Official Time Yet"
                }else{//it's real? then show it
                    this.time = DateTimeConverter.toDoubleDigit(cal.get(Calendar.HOUR_OF_DAY).toString()) + ":" + DateTimeConverter.toDoubleDigit(cal.get(Calendar.MINUTE).toString()) + " WIB"
                }
                tv_match_time_detail.text = this.date
                tv_match_detail_kick_off.text = this.time
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