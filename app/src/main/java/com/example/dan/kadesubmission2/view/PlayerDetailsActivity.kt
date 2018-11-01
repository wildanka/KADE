package com.example.dan.kadesubmission2.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.databinding.ActivityPlayerDetailsBinding
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_player_details.*

class PlayerDetailsActivity : AppCompatActivity() {

    var binding : ActivityPlayerDetailsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_player_details)
//        setContentView(R.layout.activity_player_details)
//        setSupportActionBar(toolbar)
        val playerString= intent.getStringExtra("PLAYER_NAME")

        setSupportActionBar(binding!!.tbPlayerDetails)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = playerString
        setPlayerDatas()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /*
     fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.getItemId()) {
             android.R.id.home -> {
                 onBackPressed()
                 return true
             }
         }

         return super.onOptionsItemSelected(item)
     }
 */

    private fun setPlayerDatas(){
        val playerHeight = intent.getStringExtra("PLAYER_HEIGHT")
        val playerWeight = intent.getStringExtra("PLAYER_WEIGHT")
        val playerPosition= intent.getStringExtra("POSITION")
        val playerFanart= intent.getStringExtra("PLAYER_FANART")
        val playerDescription= intent.getStringExtra("PLAYER_DESCRIPTION")
        binding!!.contentPlayerDetails!!.tvPlayerHeight.text = playerHeight
        binding!!.contentPlayerDetails!!.tvPlayerWeight.text = playerWeight
        Picasso.get().load(playerFanart).into(binding!!.contentPlayerDetails!!.ivPlayerFanart1)
        binding!!.contentPlayerDetails!!.tvPlayerDescription.text = playerDescription
        binding!!.contentPlayerDetails!!.tvPlayerPosition.text = playerPosition
    }

}
