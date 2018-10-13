package com.example.dan.footballapplication.View


import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.dan.footballapplication.Model.Club
import com.example.dan.footballapplication.R

import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar

class ClubDetailActivity : AppCompatActivity() {
    lateinit var clubDetailUI : ClubDetailAnkoUI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clubDetailUI = ClubDetailAnkoUI()
        clubDetailUI.setContentView(this)



//        val club = intent.getStringExtra("clubName")
        val club = intent.getParcelableExtra<Club>("clubName")
        clubDetailUI.textClubName.text = club.clubName
        clubDetailUI.textClubDescription.text = club.clubDesc

        //TODO: menggunakan glide pada halaman detail, STATUS : DONE
        Glide.with(this).load(club.clubLogo).into(clubDetailUI.imgClubLogo)

    }


}

class ClubDetailAnkoUI : AnkoComponent<ClubDetailActivity>{
    lateinit var textClubName: TextView
    lateinit var textClubDescription: TextView
    lateinit var imgClubLogo: ImageView

    override fun createView(ui: AnkoContext<ClubDetailActivity>) = with(ui) {
        verticalLayout{
            toolbar(){
                setTitle("Football Club")
                backgroundColor = ContextCompat.getColor(context,R.color.colorPrimary)
                setTitleTextColor(ContextCompat.getColor(context,R.color.colorWhite))
            }.lparams(width= matchParent,height= wrapContent)

            imgClubLogo = imageView(){
            }.lparams(width= dip(64),height= dip(64)){
                topMargin = dip(16)
                gravity = Gravity.CENTER_HORIZONTAL
            }

            textClubName = textView(){
            }.lparams {
                topMargin = dip(8)
                gravity = Gravity.CENTER_HORIZONTAL
            }

            textClubDescription = textView(){
            }.lparams(width= wrapContent, height= wrapContent){
                topMargin = dip(56)
                horizontalMargin = dip(16)
            }

        }
    }
}