package com.example.dan.belajarkotlin.View.AnkoUI

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.dan.belajarkotlin.R
import org.jetbrains.anko.*

class ItemClubUI : AnkoComponent<Context> {
    companion object {
        val iv_club_logo = 1
        val tv_club_name = 2
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {

        return linearLayout {
            orientation = LinearLayout.HORIZONTAL
            padding = R.dimen.vertical_margin

            imageView(R.drawable.img_acm) {
                id = R.id.iv_club_logo
            }.lparams(width = dip(50), height = dip(50)) {
                margin = dip(4)
            }
            textView {
                id = R.id.tv_club_name
            }.lparams(width = wrapContent, height = wrapContent) {
                gravity = Gravity.CENTER_VERTICAL
            }
        }
    }
}
