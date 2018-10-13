package com.example.dan.footballapplication.View.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.dan.footballapplication.Model.Club
import com.example.dan.footballapplication.View.AnkoUI.ClubListUI
import org.jetbrains.anko.AnkoContext

class ClubRecyclerAdapter(private val context: Context, var listClub: List<Club>, private val listener: (Club) -> Unit) : RecyclerView.Adapter<ClubRecyclerAdapter.ClubListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubListViewHolder {
        return ClubListViewHolder(ClubListUI().createView(AnkoContext.create(parent.context,parent)))
    }

    override fun onBindViewHolder(holder: ClubListViewHolder, position: Int) {
        val club = listClub[position]
        holder.bind(club, listener)
    }

    override fun getItemCount(): Int {
        return listClub.size
    }

    inner class ClubListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvClubName : TextView
        var ivClubLogo : ImageView

        init {
            tvClubName = itemView.findViewById(ClubListUI.tvClubName)
            ivClubLogo = itemView.findViewById(ClubListUI.ivClubLogo)
        }
        fun bind(club:Club, listener: (Club) -> Unit){
            tvClubName.text = club.clubName
            Glide.with(itemView.context).load(club.clubLogo).into(ivClubLogo)
            itemView.setOnClickListener{
                listener(club)
            }
        }


    }
}
