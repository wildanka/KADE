package com.example.dan.kadesubmission2.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.model.entity.FavoriteTeams
import com.example.dan.kadesubmission2.model.localStorage.database

import com.example.dan.kadesubmission2.view.adapter.RVTeamFavoritesAdapter
import com.example.dan.kadesubmission2.viewmodel.FavoritesTeamsFragmentViewModel
import kotlinx.android.synthetic.main.fragment_list_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoritesTeamsFragment : Fragment(){
    private val TAG = "Fragment PREV FIXTURES"
    private var favorites : MutableList<FavoriteTeams> = mutableListOf()
    private lateinit var adapter: RVTeamFavoritesAdapter
    /**
     * Initialize newInstance for passing parameters
     */
    companion object {
        fun newInstance(): FavoritesTeamsFragment {
            val fragment = FavoritesTeamsFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        showFavorite(adapter)
        super.onActivityResult(requestCode, resultCode, data)
    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView : View = inflater.inflate(R.layout.fragment_list_favorites_match,container,false)
        val rvListMatch = rootView.findViewById<RecyclerView>(R.id.rv_list_match)
        val swipeRefreshListMatch= rootView.findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_list_match)
        val mViewModel = ViewModelProviders.of(this).get(FavoritesTeamsFragmentViewModel::class.java)
        rvListMatch.layoutManager = LinearLayoutManager(activity!!, LinearLayout.VERTICAL,false)
        adapter = RVTeamFavoritesAdapter(activity!!)
        rvListMatch.adapter = adapter

        println(TAG)
        observeDataFeed(mViewModel,adapter, activity!!)
        swipeRefreshListMatch.setOnRefreshListener {
            observeDataFeed(mViewModel,adapter, activity!!)
//            showFavorite(adapter)
        }

        return rootView
    }


    private fun observeDataFeed(viewModel:FavoritesTeamsFragmentViewModel, favoritesTeamsAdapter: RVTeamFavoritesAdapter, mContext: Context){

        viewModel.getFavoritesTeams(mContext).observe(this, Observer<List<FavoriteTeams>>{ favoriteTeams ->
            if (favoriteTeams != null) {
                if (favoriteTeams.isNotEmpty()){
                    val favoriteList : MutableList<FavoriteTeams> = favoriteTeams as MutableList<FavoriteTeams>
                    progressBar.visibility = View.VISIBLE
                    favoritesTeamsAdapter.setupListTeams(favoriteList)
                    favoritesTeamsAdapter.notifyDataSetChanged()
                    progressBar.visibility = View.GONE
                    Log.d(TAG, favoriteTeams[0].teamName)
                    println("VIEW : "+favoriteTeams[0].teamName)
                }else{
                    Toast.makeText(mContext,"No Favorites Fixture Yet",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun showFavorite(favoritesAdapter: RVTeamFavoritesAdapter){
        try {
            activity!!.database.use {
                val result = select(FavoriteTeams.TABLE_FAVORITE_TEAMS)
                val favoriteTeams = result.parseList(classParser<FavoriteTeams>())
                favorites.addAll(favoriteTeams)
//                val fixList : FixtureList = FixtureList()
                favoritesAdapter.notifyDataSetChanged()
                favoritesAdapter.setupListTeams(favorites)
            }
        }catch (e: SQLiteConstraintException){
            println("Error while inserting data to database: ${ e?.message }")
            Log.getStackTraceString(e)
//            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }
}