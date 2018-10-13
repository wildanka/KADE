package com.example.dan.footballapplication.View.AnkoUI

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*

class ClubListUI : AnkoComponent<ViewGroup>{

    companion object {
        val ivClubLogo = 1
        val tvClubName = 2
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){
        linearLayout{
            lparams(matchParent, wrapContent)
            padding = dip(16)

            imageView {
                id = ivClubLogo
                layoutParams = LinearLayout.LayoutParams(dip(50), dip(50))
            }
            textView {
                id = tvClubName
                layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                text= "test"
                gravity = Gravity.CENTER_VERTICAL
//                textSize = 14f
            }.lparams { margin = dip(16) }
        }
    }

}