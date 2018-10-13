package com.example.dan.kadesubmission2.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.model.entity.FixtureFeed
import com.example.dan.kadesubmission2.repository.FixtureRepository
import com.example.dan.kadesubmission2.view.adapter.ViewPagerAdapter
import com.example.dan.kadesubmission2.view.fragment.NextFixturesFragment
import com.example.dan.kadesubmission2.view.fragment.PrevFixturesFragment
import com.example.dan.kadesubmission2.viewmodel.PrevFixturesFragmentViewModel

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "Fragment PREV FIXTURES"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        println(TAG)

        //setting the tablayout
        createTabLayout()

    }

    private fun createTabLayout(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(PrevFixturesFragment(), "Prev Match")
        adapter.addFragment(NextFixturesFragment(), "Next Match")
        viewpager.adapter = adapter
        tab_layout.setupWithViewPager(viewpager)
    }

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
}
