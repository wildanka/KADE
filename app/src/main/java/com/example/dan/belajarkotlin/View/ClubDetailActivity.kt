package com.example.dan.belajarkotlin.View

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.example.dan.belajarkotlin.Model.Club
import com.example.dan.belajarkotlin.R

import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar

class ClubDetailActivity : AppCompatActivity() {
    lateinit var clubDetailUI : ClubDetailAnkoUI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clubDetailUI = ClubDetailAnkoUI()
        clubDetailUI.setContentView(this)
        val club = intent.getParcelableExtra<Club>("club_extra")
        clubDetailUI.textClubName.text = club.clubName
        clubDetailUI.textClubDescription.text = club.clubDesc
        clubDetailUI.imgClubLogo.imageResource = club.clubLogo!!

        //region xml
  /*      setContentView(R.layout.activity_anko_test)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)*/
        //endregion xml
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