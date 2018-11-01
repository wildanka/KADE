package com.example.dan.kadesubmission2.view.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.model.entity.FavoriteTeams
import com.example.dan.kadesubmission2.model.entity.TeamLogo
import com.example.dan.kadesubmission2.view.TeamDetailsActivity
import com.squareup.picasso.Picasso

class RVTeamFavoritesAdapter(private val mContext: Context) : RecyclerView.Adapter<RVTeamFavoritesAdapter.RVHolderTeams>(){
    val inflater : LayoutInflater

    var listTeams : List<FavoriteTeams> = mutableListOf()
    init {
        inflater = LayoutInflater.from(mContext)
    }

    fun setupListTeams(teams : List<FavoriteTeams>){
        this.listTeams = teams
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVTeamFavoritesAdapter.RVHolderTeams {
        return RVHolderTeams(inflater.inflate(R.layout.item_list_teams,parent,false))
    }

    override fun getItemCount(): Int {
        return listTeams.size
    }

    override fun onBindViewHolder(holder: RVTeamFavoritesAdapter.RVHolderTeams, position: Int) {
        holder.bind(listTeams[position])
    }

    inner class RVHolderTeams(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivClubLogo = itemView.findViewById<ImageView>(R.id.iv_club_logo_list_teams)
        val tvClubName = itemView.findViewById<TextView>(R.id.tv_club_name_list_teams)
//        val cardViewListMatch = itemView.findViewById<CardView>(R.id.cv_recycler_item)

        fun bind(teams : FavoriteTeams){
            Picasso.get().load(teams.teamLogoUrl).into(ivClubLogo)
            tvClubName.text = teams.teamName

            itemView.setOnClickListener{
                mContext.startActivity(Intent(mContext, TeamDetailsActivity::class.java)
                        .putExtra("ID_CLUB",teams.idTeams)
                        .putExtra("CLUB_NAME",teams.teamName)
                        .putExtra("CLUB_LOGO_URL",teams.teamLogoUrl)
                        .putExtra("CLUB_STADIUM",teams.teamStadium)
                        .putExtra("CLUB_YEARS",teams.teamYears)
                )
            }
        }

    }
}