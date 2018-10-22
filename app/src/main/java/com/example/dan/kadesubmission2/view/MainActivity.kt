package com.example.dan.kadesubmission2.view

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.model.localStorage.database
import com.example.dan.kadesubmission2.view.fragment.FavoritesFragment
import com.example.dan.kadesubmission2.view.fragment.NextFixturesFragment
import com.example.dan.kadesubmission2.view.fragment.PrevFixturesFragment

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "Fragment PREV FIXTURES"

    private var container: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        println(TAG)

        bottom_navbar.setOnNavigationItemSelectedListener {item ->
            when(item.itemId){
                R.id.navigation_prev_match -> {
                    loadPrevFragment(savedInstanceState)
                }
                R.id.navigation_next_match ->{
                    loadNextFragment(savedInstanceState)
                }
                R.id.navigation_favorites ->{
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }

        bottom_navbar.selectedItemId = R.id.navigation_next_match

//        addFragment(fragment)
    }

    /*private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){
            R.id.navigation_prev_match->{
                val fragment = PrevFixturesFragment.Companion.newInstance()
                addFragment(fragment)

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_next_match->{
                val fragment = NextFixturesFragment.Companion.newInstance()
                loadPrevMatch(savedInstanceState, fragment)

                return@OnNavigationItemSelectedListener true
            }
           R.id.navigation_favorites->{
                val fragment = NextMenuFragment.Companion.newInstance()
                addFragment(fragment)

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }*/

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

/*
    private fun addFragment(fragment: Fragment){
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in,R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.container,fragment,fragment.javaClass.simpleName)
                .commit()
    }
*/

    private fun loadPrevFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.design_bottom_sheet_slide_in,R.anim.design_bottom_sheet_slide_out)
                    .replace(R.id.container,PrevFixturesFragment(),PrevFixturesFragment::class.java.simpleName)
                    .commit()
        }
    }
    private fun loadNextFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.design_bottom_sheet_slide_in,R.anim.design_bottom_sheet_slide_out)
                    .replace(R.id.container,NextFixturesFragment(),NextFixturesFragment::class.java.simpleName)
                    .commit()
        }
    }
    private fun loadFavoritesFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.design_bottom_sheet_slide_in,R.anim.design_bottom_sheet_slide_out)
                    .replace(R.id.container,FavoritesFragment(),FavoritesFragment::class.java.simpleName)
                    .commit()
        }

    }


}