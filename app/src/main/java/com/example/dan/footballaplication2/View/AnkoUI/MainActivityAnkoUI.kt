package com.example.dan.footballaplication2.View.AnkoUI

import android.support.design.R.attr.colorAccent
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.dan.footballaplication2.R
import com.example.dan.footballaplication2.View.MainActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MainActivityAnkoUI : AnkoComponent<MainActivity> {
    lateinit var rvListClub: RecyclerView
    lateinit var tbMainActivity: Toolbar
    private lateinit var progressBar: ProgressBar
    lateinit var spLiga: Spinner
    lateinit var swipeRefresh: SwipeRefreshLayout
    lateinit var ivGithub: ImageView
    lateinit var tvUsername: TextView
    lateinit var tvCompany: TextView

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            ivGithub = imageView {

            }.lparams(width = dip(60), height = dip(50))

            tvUsername = textView {
            }.lparams(width = wrapContent, height = wrapContent)

            tvCompany = textView {
            }.lparams(width = wrapContent, height = wrapContent)

            spLiga = spinner()
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    rvListClub = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }

        }
        //region belajar
/*
        linearLayout {
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            */
/*tbMainActivity = toolbar() {
                title = "Football Club"
                backgroundColor = android.support.v4.content.ContextCompat.getColor(context, R.color.colorPrimary)
                setTitleTextColor(android.support.v4.content.ContextCompat.getColor(context, R.color.colorWhite))
            }.lparams(width = matchParent, height = wrapContent)
*//*

            spLiga = spinner()
            swipeRefresh = swipeRefreshLayout(
                 */
/*   setColorSchemeResources(colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)*//*

                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        rvListClub = recyclerView {
                            lparams(width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(ctx)
                        }

                        progressBar = progressBar {
                        }.lparams {
                            centerHorizontally()
                        }
                    }
            )
           */
/* rvListClub = recyclerView() {
            }.lparams(width = matchParent, height = matchParent)*//*


        }
*/

        //endregion
    }

}