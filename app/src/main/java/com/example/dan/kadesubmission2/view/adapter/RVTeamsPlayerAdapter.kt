package com.example.dan.kadesubmission2.view.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.model.entity.TeamLogo
import com.example.dan.kadesubmission2.model.entity.TeamPlayersDetail
import com.example.dan.kadesubmission2.view.PlayerDetailsActivity
import com.example.dan.kadesubmission2.view.TeamDetailsActivity
import com.squareup.picasso.Picasso

class RVTeamsPlayerAdapter(private val mContext: Context) : RecyclerView.Adapter<RVTeamsPlayerAdapter.RVHolderPlayers>(){
    val inflater : LayoutInflater

    var listPlayer : List<TeamPlayersDetail> = mutableListOf()
    init {
        inflater = LayoutInflater.from(mContext)
    }

    fun setupListTeams(players : List<TeamPlayersDetail>){
        this.listPlayer = players
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVTeamsPlayerAdapter.RVHolderPlayers {
        return RVHolderPlayers(inflater.inflate(R.layout.item_list_player,parent,false))
    }

    override fun getItemCount(): Int {
        return listPlayer.size
    }

    override fun onBindViewHolder(holder: RVTeamsPlayerAdapter.RVHolderPlayers, position: Int) {
        holder.bind(listPlayer[position])
    }

    inner class RVHolderPlayers(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPlayersCutout= itemView.findViewById<ImageView>(R.id.iv_player_pic_list_teams)
        val tvPlayersName = itemView.findViewById<TextView>(R.id.tv_player_name_list_teams)
        val tvPlayersPositions = itemView.findViewById<TextView>(R.id.tv_player_position_list_teams)
//        val cardViewListMatch = itemView.findViewById<CardView>(R.id.cv_recycler_item)

        fun bind(players : TeamPlayersDetail){
            Picasso.get().load(players.strCutout).into(ivPlayersCutout)
            tvPlayersName.text = players.strPlayerName
            tvPlayersPositions.text = players.strPosition

            itemView.setOnClickListener{
                mContext.startActivity(Intent(mContext, PlayerDetailsActivity::class.java)
                        .putExtra("PLAYER_ID",players.playerID)
                        .putExtra("PLAYER_NAME",players.strPlayerName)
                        .putExtra("PLAYER_FANART",players.strFanart1)
                        .putExtra("POSITION",players.strPosition)
                        .putExtra("PLAYER_HEIGHT",players.strHeight)
                        .putExtra("PLAYER_WEIGHT",players.strWeight)
                        .putExtra("PLAYER_DESCRIPTION",players.strDescriptionEN)
                )
            }
        }

    }
}