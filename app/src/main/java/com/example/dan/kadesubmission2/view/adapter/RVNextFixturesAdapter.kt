package com.example.dan.kadesubmission2.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.CalendarContract
import android.provider.CalendarContract.Events.*
import android.support.annotation.RequiresApi
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.model.entity.FixtureList
import com.example.dan.kadesubmission2.util.DateTimeConverter
import com.example.dan.kadesubmission2.view.DetailFixturesActivity
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class RVNextFixturesAdapter(private val mContext: Context) : RecyclerView.Adapter<RVNextFixturesAdapter.RVHolder>(){
    val inflater : LayoutInflater
    var listFixture : List<FixtureList> = mutableListOf()

    init {
        inflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVHolder {
        return RVHolder(inflater.inflate(R.layout.item_list_next_match,parent,false))
    }

    override fun getItemCount(): Int {
        return listFixture.size
    }

    fun setupListFixture(fixtures : MutableList<FixtureList>){
        this.listFixture = fixtures
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RVHolder, position: Int) {
        holder.bind(listFixture[position])
    }

    inner class RVHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvHomeClub = itemView.findViewById<TextView>(R.id.tv_next_home_club)
        val tvAwayClub = itemView.findViewById<TextView>(R.id.tv_next_away_club)
        val tvHomeClubScore = itemView.findViewById<TextView>(R.id.tv_next_home_club_score)
        val tvAwayClubScore = itemView.findViewById<TextView>(R.id.tv_next_away_club_score)
        val tvMatchDate= itemView.findViewById<TextView>(R.id.tv_next_match_date)
        val tvMatchTime = itemView.findViewById<TextView>(R.id.tv_next_match_hours)
        val cardViewListMatch = itemView.findViewById<CardView>(R.id.cv_next_recycler_item)
        val ibAddToReminder = itemView.findViewById<ImageButton>(R.id.imgbtn_next_fixtures)

        fun bind(fixture : FixtureList){
            tvHomeClub.text = fixture.homeClub
            tvAwayClub.text = fixture.awayClub
            tvHomeClubScore.text = fixture.homeClubScore
            tvAwayClubScore.text = fixture.awayClubScore
            tvMatchDate.text = DateTimeConverter.rawStringToDateConverter(fixture.date!!)
            var jam= DateTimeConverter.tambahGMT(fixture.timeEvent!!.substring(0,2))
            var menit= fixture.timeEvent!!.substring(3,5)
            tvMatchTime.text = "$jam:$menit WIB"
            itemView.setOnClickListener{
                mContext.startActivity(Intent(mContext,DetailFixturesActivity::class.java)
                        .putExtra("ID_CLUB_HOME",fixture.idHomeTeam)
                        .putExtra("ID_CLUB_AWAY",fixture.idAwayTeam)
                        .putExtra("ID_EVENT",fixture.idEvent)
                )
            }


            //untuk penambahan ke kalender
            var tahun = fixture.date!!.substring(0,4)
            var bulan = fixture.date!!.substring(5,7)
            var tanggal = fixture.date!!.substring(8,10)
            println("$tahun bulan : $bulan, tanggal : $tanggal | Jam : $jam, menit : $menit ")

            var fixtureCalendarBegin : Calendar = Calendar.getInstance()
            var fixtureCalendarEnd : Calendar = Calendar.getInstance()
            fixtureCalendarBegin.set(tahun.toInt(),bulan.toInt(),tanggal.toInt(),jam.toInt(),menit.toInt())
//            fixtureCalendarEnd.set(tahun.toInt(),bulan.toInt(),tanggal.toInt(),jam.toInt()+1,menit.toInt())
            ibAddToReminder.setOnClickListener{
                val intent = Intent(Intent.ACTION_INSERT)
                        .setData(CONTENT_URI)
                        .putExtra(TITLE,fixture.homeClub+" vs "+fixture.awayClub)
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, fixtureCalendarBegin.timeInMillis)
                mContext.startActivity(intent)
            }
        }
    }

}