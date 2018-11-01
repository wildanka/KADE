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
import com.example.dan.kadesubmission2.model.entity.Favorite
import com.example.dan.kadesubmission2.view.DetailFixturesActivity

class RVFixturesAdapter(private val mContext: Context) : RecyclerView.Adapter<RVFixturesAdapter.RVHolder>(){
    val inflater : LayoutInflater
    var listFixture : List<Favorite> = mutableListOf()

    init {
        inflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVHolder {
        return RVHolder(inflater.inflate(R.layout.item_list_match,parent,false))
    }

    override fun getItemCount(): Int {
        return listFixture.size
    }

    fun setupListFixture(fixtures : MutableList<Favorite>){
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
        val tvMatchHour = itemView.findViewById<TextView>(R.id.tv_match_hours)
        val cardViewListMatch = itemView.findViewById<CardView>(R.id.cv_recycler_item)

        fun bind(fixture : Favorite){
            tvHomeClub.text = fixture.homeTeam
            tvAwayClub.text = fixture.awayTeam

                if (fixture.homeScore == "null"){
                    tvHomeClubScore.text = "-"
                }else{
                    tvHomeClubScore.text = fixture.homeScore
                }

                if (fixture.awayScore == "null"){
                    tvAwayClubScore.text = "-"
                }else{
                    tvAwayClubScore.text = fixture.awayScore
                }

                tvMatchDate.text = fixture.eventDate
                itemView.setOnClickListener{
                    mContext.startActivity(Intent(mContext,DetailFixturesActivity::class.java)
                            .putExtra("ID_CLUB_HOME",fixture.homeID)
                            .putExtra("ID_CLUB_AWAY",fixture.awayID)
                            .putExtra("ID_EVENT",fixture.idEvent)
                    )

            }
        }
    }
}