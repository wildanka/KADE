package com.example.dan.kadesubmission2.view.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.model.entity.FixtureList
import com.example.dan.kadesubmission2.util.DateTimeConverter
import com.example.dan.kadesubmission2.view.DetailFixturesActivity
import java.util.*

class RVSearchFixturesAdapter(private val mContext: Context) : RecyclerView.Adapter<RVSearchFixturesAdapter.RVHolder>(){
    val inflater : LayoutInflater
    var listFixture : List<FixtureList> = mutableListOf()

    init {
        inflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVHolder {
        return RVHolder(inflater.inflate(R.layout.item_list_match,parent,false))
    }

    override fun getItemCount(): Int {
        return listFixture.size
    }

    fun setupListFixture(fixtures : List<FixtureList>){
        this.listFixture = fixtures
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RVHolder, position: Int) {
        holder.bind(listFixture[position])
    }

    inner class RVHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvHomeClub = itemView.findViewById<TextView>(R.id.tv_home_club)
        val tvAwayClub = itemView.findViewById<TextView>(R.id.tv_away_club)
        val tvHomeClubScore = itemView.findViewById<TextView>(R.id.tv_home_club_score)
        val tvAwayClubScore = itemView.findViewById<TextView>(R.id.tv_away_club_score)
        val tvMatchDate= itemView.findViewById<TextView>(R.id.tv_match_date)
        val tvMatchTime = itemView.findViewById<TextView>(R.id.tv_match_hours)
        val cardViewListMatch = itemView.findViewById<CardView>(R.id.cv_recycler_item)

        fun bind(fixture : FixtureList){
            tvHomeClub.text = fixture.homeClub
            tvAwayClub.text = fixture.awayClub
            tvHomeClubScore.text = fixture.homeClubScore
            tvAwayClubScore.text = fixture.awayClubScore
            //convert heula
            println("RVSearchFixtures : ${fixture.strDate}")
            println("RVSearchFixtures : ${fixture.dateEvent}")
            println("RVSearchFixtures : ${fixture.timeEvent}")

            //jika strDate not null
            if (fixture.strDate != null && fixture.timeEvent != null) {
                val strDate  = DateTimeConverter.toGMTFormat(fixture.strDate!!,fixture.timeEvent!!)
                val cal : Calendar = Calendar.getInstance()
                cal.time = strDate
                tvMatchDate.text = "${DateTimeConverter.dayConverter(strDate!!.day)}, ${strDate!!.date.toString()} ${DateTimeConverter.monthConverter(cal.get(Calendar.MONTH))} ${cal.get(Calendar.YEAR)}"
                tvMatchTime.text = DateTimeConverter.toDoubleDigit(cal.get(Calendar.HOUR_OF_DAY).toString())+":"+DateTimeConverter.toDoubleDigit(cal.get(Calendar.MINUTE).toString())+" WIB"
            }else {
                if (fixture.strDate == null) {
                    if (fixture.dateEvent == null) {
                        //jika strDate & dateEvent null tampilkan '-'
                        tvMatchDate.text = "-"
                    }else{
                        //jika strDate saja yang null tampilkan dateEvent
                        tvMatchDate.text = fixture.dateEvent
                    }
                }

                //jika timeEvent null
                if (fixture.timeEvent == null){
                    tvMatchTime.text = "-"
                }else{
                    //jika timEvent tidak null
                    tvMatchTime.text = fixture.timeEvent
                }
                tvMatchDate.text = fixture.dateEvent
            }

            //jika timeEvent not null
            if (fixture.timeEvent != null) {
                val strDate  = DateTimeConverter.toGMTFormat("03/11/18",fixture.timeEvent!!)
                val cal : Calendar = Calendar.getInstance()
                cal.time = strDate
                tvMatchTime.text = DateTimeConverter.toDoubleDigit(cal.get(Calendar.HOUR_OF_DAY).toString())+":"+DateTimeConverter.toDoubleDigit(cal.get(Calendar.MINUTE).toString())+" WIB"
            }else{
                tvMatchTime.text = "-"
            }

            if (fixture.dateEvent != null && fixture.timeEvent != null){ //jika dateEvent dan timeEvent not null, manfaatkan dateEvent
                val strYear = fixture.dateEvent!!.substring(2,4)
                val strM = fixture.dateEvent!!.substring(5,7)
                val strDay = fixture.dateEvent!!.substring(8,10)
                println("HASIL SEARCH $strYear / $strM / $strDay | id: ${fixture.idEvent}")
                val strDateResult  = DateTimeConverter.toGMTFormat("$strDay/$strM/$strYear",fixture.timeEvent!!)
                val cal : Calendar = Calendar.getInstance()
                cal.time = strDateResult
                tvMatchDate.text = DateTimeConverter.toSimpleString(strDateResult).toString()
                tvMatchTime.text = DateTimeConverter.toDoubleDigit(cal.get(Calendar.HOUR_OF_DAY).toString())+":"+DateTimeConverter.toDoubleDigit(cal.get(Calendar.MINUTE).toString())+" WIB"
            }

            itemView.setOnClickListener{
                mContext.startActivity(Intent(mContext,DetailFixturesActivity::class.java)
                        .putExtra("ID_CLUB_HOME",fixture.idHomeTeam)
                        .putExtra("ID_CLUB_AWAY",fixture.idAwayTeam)
                        .putExtra("ID_EVENT",fixture.idEvent)
                )
            }
        }
    }
}