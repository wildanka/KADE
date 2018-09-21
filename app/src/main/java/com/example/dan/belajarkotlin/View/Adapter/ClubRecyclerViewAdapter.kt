package com.example.dan.belajarkotlin.View.Adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.dan.belajarkotlin.Model.Club
import com.example.dan.belajarkotlin.R
import com.example.dan.belajarkotlin.View.AnkoUI.ItemClubUI
import com.example.dan.belajarkotlin.View.ClubDetailActivity
import org.jetbrains.anko.*

/**
 * Created by DAN on 8/31/2018.
 */
class ClubRecyclerViewAdapter(private val context: Context, private val listClub: List<Club>) : RecyclerView.Adapter<ClubRecyclerViewAdapter.ClubViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        return ClubViewHolder(ItemClubUI().createView(AnkoContext.create(parent.context,true)))
    }
    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        val club = listClub[position]
        holder.tvClubName.text = club.clubName

        /*     holder.itemView.setOnClickListener { view ->
                 Toast.makeText(context, listClub[position].clubName,Toast.LENGTH_SHORT).show()
                 val intent = Intent(context, ClubDetailActivity::class.java)
                 intent.putExtra("club_extra",listClub[position])
                 startActivity(context,intent,null)

     //            startActivity(intentFor<ClubDetailActivity>("club_extra",listClub[position]).singleTop())
             }*/
    }

    /*
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) : ClubViewHolder {
            //        ClubViewHolder(ClubItemUI().createView(AnkoContext.create(parent.context,parent)))
    //        return ClubViewHolder(LunchMenuItemUI().createView(AnkoContext.create(parent!!.context)))
            return ClubViewHolder(LunchMenuItemUI().createView(context))
    //            LayoutInflater.from(context).inflate(R.layout.item_club_list,parent,false)
        }
    */
    override fun getItemCount(): Int {
        return listClub.size
    }



    class ClubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvClubName : TextView
        val ivClubLogo : ImageView
//        val tvClubDesc = tv_club_desc

        init{
            tvClubName = itemView.findViewById(ItemClubUI.tv_club_name)
            ivClubLogo = itemView.findViewById(ItemClubUI.iv_club_logo)
        }
       /* fun bind(club: Club){
            tvClubName.text = club.clubName
            Glide.with(itemView.context).load(club.clubLogo).into(ivClubLogo)
        }*/
    }
}
/*

class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    val tvClubName : TextView = itemView?.findViewById(R.id.tv_club_name) as TextView
    val ivClubLogo : ImageView = itemView?.findViewById(R.id.iv_club_logo) as ImageView

    fun bind(club: Club){
        tvClubName.text = club.clubName
        Glide.with(itemView.context).load(club.clubLogo).into(ivClubLogo)
        tvClubName.setText(club.clubName)
    }
}*/
