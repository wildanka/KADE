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
import com.example.dan.kadesubmission2.model.entity.Favorite
import com.example.dan.kadesubmission2.model.localStorage.database

import com.example.dan.kadesubmission2.view.adapter.RVFixturesAdapter
import com.example.dan.kadesubmission2.viewmodel.FavoritesFragmentViewModel
import kotlinx.android.synthetic.main.fragment_list_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoritesFragment : Fragment(){
    private val TAG = "Fragment PREV FIXTURES"
    private var favorites : MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: RVFixturesAdapter
    /**
     * Initialize newInstance for passing parameters
     */
    companion object {
        fun newInstance(): FavoritesFragment {
            val prevFixturesFragment = FavoritesFragment()
            val args = Bundle()
            prevFixturesFragment.arguments = args
            return prevFixturesFragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView : View = inflater.inflate(R.layout.fragment_list_favorites_match,container,false)
        val rvListMatch = rootView.findViewById<RecyclerView>(R.id.rv_list_match)
        val swipeRefreshListMatch= rootView.findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_list_match)
        val mViewModel = ViewModelProviders.of(this).get(FavoritesFragmentViewModel::class.java)
        rvListMatch.layoutManager = LinearLayoutManager(activity!!, LinearLayout.VERTICAL,false)
        adapter = RVFixturesAdapter(activity!!)
        rvListMatch.adapter = adapter

        println(TAG)
        observeDataFeed(mViewModel,adapter, activity!!)
        swipeRefreshListMatch.setOnRefreshListener {
            observeDataFeed(mViewModel,adapter, activity!!)
//            showFavorite(adapter)
        }

        return rootView
    }


    private fun observeDataFeed(viewModel:FavoritesFragmentViewModel, favoritesAdapter: RVFixturesAdapter, mContext: Context){

        viewModel.getFavoritesFixture(mContext).observe(this, Observer<List<Favorite>>{ favoriteFixtures ->
            if (favoriteFixtures != null) {
                if (favoriteFixtures.isNotEmpty()){
                    val favoriteList : MutableList<Favorite> = favoriteFixtures as MutableList<Favorite>
                    progressBar.visibility = View.VISIBLE
                    favoritesAdapter.setupListFixture(favoriteList)
                    favoritesAdapter.notifyDataSetChanged()
                    progressBar.visibility = View.GONE
                    Log.d(TAG, favoriteFixtures[0].awayTeam)
                    println("VIEW : "+favoriteFixtures[0].homeTeam)
                }else{
                    Toast.makeText(mContext,"No Favorites Fixture Yet",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun showFavorite(favoritesAdapter: RVFixturesAdapter){
        try {
            activity!!.database.use {
                val result = select(Favorite.TABLE_FAVORITE_EVENTS)
                val favorite = result.parseList(classParser<Favorite>())
                favorites.addAll(favorite)
//                val fixList : FixtureList = FixtureList()
                favoritesAdapter.notifyDataSetChanged()
                favoritesAdapter.setupListFixture(favorites)
            }
        }catch (e: SQLiteConstraintException){
            println("Error while inserting data to database: ${ e?.message }")
            Log.getStackTraceString(e)
//            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }
}