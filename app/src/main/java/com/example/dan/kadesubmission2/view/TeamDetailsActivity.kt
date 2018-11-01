package com.example.dan.kadesubmission2.view

import android.database.sqlite.SQLiteConstraintException
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.databinding.ActivityTeamDetailsBinding
import com.example.dan.kadesubmission2.model.entity.Favorite
import com.example.dan.kadesubmission2.model.entity.FavoriteTeams
import com.example.dan.kadesubmission2.model.localStorage.database
import com.example.dan.kadesubmission2.view.adapter.ViewPagerAdapter
import com.example.dan.kadesubmission2.view.fragment.TeamDetailsOverviewFragment
import com.example.dan.kadesubmission2.view.fragment.TeamDetailsPlayersFragment
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_team_details.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamDetailsActivity : AppCompatActivity() {
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var binding : ActivityTeamDetailsBinding? = null
    private lateinit var idClub : String
    private lateinit var urlClubLogo : String
    private lateinit var strClubName : String
    private lateinit var strClubStadium : String
    private lateinit var intFormedYear : String

    override fun onCreate(savedInstanceState: Bundle?) {
        idClub = intent.getStringExtra("ID_CLUB")
        strClubName = intent.getStringExtra("CLUB_NAME")
        urlClubLogo = intent.getStringExtra("CLUB_LOGO_URL")
        strClubStadium = intent.getStringExtra("CLUB_STADIUM")
        intFormedYear = intent.getStringExtra("CLUB_YEARS")
        checkFavoriteState()
        setFavorite()
        println("Club Favorit : $isFavorite")

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_team_details)
        Picasso.get().load(urlClubLogo).into(binding!!.ivClubLogoTeamDetails)
        binding!!.tvClubNameTeamDetails.text = strClubName
        binding!!.tvStadium.text = strClubStadium
        binding!!.tvYears.text = intFormedYear

        setSupportActionBar(binding!!.tbDetailTeams)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        createViewPager()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorites, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home ->{
                finish()
                true
            }
            R.id.add_to_favorites ->{
                if (::idClub.isInitialized || ::strClubName.isInitialized|| ::urlClubLogo.isInitialized){
                    if(isFavorite) removeFromFavorite() else addToFavorite()
                    isFavorite = !isFavorite
                    setFavorite()
                }else{
                    Toast.makeText(this,"Still Retrieving Club Data from Network, Please Try Again", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(FavoriteTeams.TABLE_FAVORITE_TEAMS,
                        FavoriteTeams.ID_TEAMS to idClub,
                        FavoriteTeams.TEAM_NAME to strClubName,
                        FavoriteTeams.TEAM_LOGO_URL to urlClubLogo,
                        FavoriteTeams.TEAM_STADIUM to strClubStadium,
                        FavoriteTeams.TEAM_YEARS to intFormedYear)
            }
//            println("BERHASIL INSERT HOREE")
            Snackbar.make(
                    root_team_details,
                    "Match Added to Favorites",
                    Snackbar.LENGTH_LONG
            ).setAction(
                    "UNDO",
                    {
                        removeFromFavorite()
                        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
                    }
            ).show()
        } catch (e: SQLiteConstraintException){
            println("Error while inserting data to database: ${ e?.message }")
            Log.getStackTraceString(e)
            Snackbar.make(root_team_details, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteTeams.TABLE_FAVORITE_TEAMS, "(ID_TEAMS = {id})","id" to idClub)
            }
            println("BERHASIL DELETE HOREE")
            val snackbar = Snackbar.make(
                    root_team_details,
                    "Clubs Removed from Favorites",
                    Snackbar.LENGTH_LONG
            ).setAction(
                    "UNDO",
                    {
                        addToFavorite()
                        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
                    }
            ).show()

        } catch (e: SQLiteConstraintException){
            println("Error while Deleting Club data from database: ${ e?.message }")
            Log.getStackTraceString(e)
        }
    }

    private fun checkFavoriteState(){
        try{
            database.use {
                val result = select(FavoriteTeams.TABLE_FAVORITE_TEAMS)
                        .whereArgs("(ID_TEAMS = {id})",
                                "id" to idClub)
                val favorite = result.parseList(classParser<FavoriteTeams>())
                if (!favorite.isEmpty()) isFavorite = true
            }
        }catch(e: SQLiteConstraintException){
            println("Error Getting Club data from database: ${ e?.message }")
        }
    }

    private fun createViewPager(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(TeamDetailsOverviewFragment.newInstance(idClub),"Overview")
        adapter.addFragment(TeamDetailsPlayersFragment.newInstance(idClub),"Players")
        binding!!.vpTeamDetails.adapter = adapter
        binding!!.tlTeamDetails.setupWithViewPager(binding!!.vpTeamDetails)
    }


}
