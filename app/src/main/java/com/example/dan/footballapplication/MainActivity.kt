package com.example.dan.footballapplication

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.dan.footballapplication.Model.Club
import com.example.dan.footballapplication.View.Adapter.ClubRecyclerAdapter
import com.example.dan.footballapplication.View.ClubDetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.recyclerview.v7.recyclerView


class MainActivity : AppCompatActivity() {
    private var clubList : MutableList<Club> = mutableListOf()
    lateinit var mainUI : MainActivityAnkoUI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainUI = MainActivityAnkoUI()
        mainUI.setContentView(this)
        //initdata dan inflate ke adapter
        initData()

        //region inflate recyclerview to adapter
        mainUI.rvListClub.layoutManager = LinearLayoutManager(this)
        mainUI.rvListClub.adapter = ClubRecyclerAdapter(this,clubList){
            val toast = Toast.makeText(applicationContext, it.clubName, Toast.LENGTH_SHORT)
            toast.show()
            //TODO : menggunakan anko common Layout, STATUS : DONE
            startActivity(intentFor<ClubDetailActivity>("clubName" to Club(it.clubName,it.clubLogo,it.clubDesc)))
        }
        //endregion

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

    private fun initData(){
        val logo = resources.obtainTypedArray(R.array.club_image)
        val name = resources.getStringArray(R.array.club_name)
        val desc = resources.getStringArray(R.array.club_desc)
        clubList.clear()
        for (i in name.indices){
            clubList.add(i,
                    Club(name[i],logo.getResourceId(i,0),desc[i] )
            )
        }

        //Recycled the typed array
        logo.recycle()
    }

}

class MainActivityAnkoUI : AnkoComponent<MainActivity> {
    lateinit var rvListClub: RecyclerView
    lateinit var tbListClub: Toolbar
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            tbListClub = toolbar() {
                title = "Football Club"
                backgroundColor = android.support.v4.content.ContextCompat.getColor(context, R.color.colorPrimary)
                setTitleTextColor(android.support.v4.content.ContextCompat.getColor(context, R.color.colorWhite))
            }.lparams(width = matchParent, height = wrapContent)

            rvListClub = recyclerView() {

            }.lparams(width = matchParent, height = matchParent)

        }
    }
}