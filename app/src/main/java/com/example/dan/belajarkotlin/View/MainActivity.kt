package com.example.dan.belajarkotlin.View

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.dan.belajarkotlin.Model.Club
import com.example.dan.belajarkotlin.R
import com.example.dan.belajarkotlin.View.Adapter.ClubRecyclerViewAdapter

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

        //region fab
        /*
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        */
        //endregion fab

        //initdata dan inflate ke adapter
        initData()

        //region inflate recyclerview to adapter
        mainUI.rvListClub.layoutManager = LinearLayoutManager(this)
        mainUI.rvListClub.adapter = ClubRecyclerViewAdapter(this,clubList)
        //endregion inflate recyclerview to adapter

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

class MainActivityAnkoUI : AnkoComponent<MainActivity>{
    lateinit var rvListClub: RecyclerView
    lateinit var tbListClub: Toolbar
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout{
            tbListClub = toolbar(){
                setTitle("Football Club")
                backgroundColor = ContextCompat.getColor(context,R.color.colorPrimary)
                setTitleTextColor(ContextCompat.getColor(context,R.color.colorWhite))
            }.lparams(width= matchParent,height= wrapContent)

            rvListClub = recyclerView(){

            }.lparams(width= matchParent,height = matchParent)

        }
    }
}

class ClubItemUI : AnkoComponent<ViewGroup> {
    lateinit var ivClubLogo : ImageView
    lateinit var tvClubName : TextView
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){
        linearLayout {
            orientation = LinearLayout.HORIZONTAL
            padding = dip(16)

            ivClubLogo = imageView(R.drawable.img_barca).lparams(width = dip(50), height = dip(50))
            tvClubName = textView().lparams(width = wrapContent, height = wrapContent) {
                gravity = Gravity.CENTER_VERTICAL
                margin = dip(10)
            }
        }
    }

}