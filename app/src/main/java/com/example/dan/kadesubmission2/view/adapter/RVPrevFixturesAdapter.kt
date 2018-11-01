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

class RVPrevFixturesAdapter(private val mContext: Context) : RecyclerView.Adapter<RVPrevFixturesAdapter.RVHolder>(){
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

    fun setupListFixture(fixtures : MutableList<FixtureList>){
        this.listFixture = fixtures
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RVHolder, position: Int) {
        holder.bind(listFixture[position])
        /*val fixture = listFixture[position]
        holder.tvHomeClub.text = fixture.homeClub
        holder.tvAwayClub.text = fixture.awayClub
        holder.tvHomeClubScore.text = fixture.homeClubScore
        holder.tvAwayClubScore.text = fixture.awayClubScore
        holder.tvMatchTime.text = fixture.date

        holder.cardViewListMatch.setOnClickListener {
            val intent = Intent(mContext,DetailFixturesActivity::class.java).apply {
                putExtra("ID_CLUB_HOME",fixture.idHomeTeam)
                putExtra("ID_CLUB_AWAY",fixture.idAwayTeam)
                putExtra("ID_EVENT",fixture.idEvent)
            }
            startActivity(mContext,intent,null)
        }*/
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
        }
    }
}