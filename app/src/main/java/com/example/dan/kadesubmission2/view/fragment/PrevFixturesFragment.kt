package com.example.dan.kadesubmission2.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
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
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.model.entity.FixtureFeed
import com.example.dan.kadesubmission2.view.adapter.RVAdapter
import com.example.dan.kadesubmission2.viewmodel.PrevFixturesFragmentViewModel
import kotlinx.android.synthetic.main.fragment_list_match.*

class PrevFixturesFragment : Fragment(){
    private val TAG = "Fragment PREV FIXTURES"
    /**
     * Initialize newInstance for passing parameters
     */
    /*companion object {
        fun newInstance(): PrevFixturesFragment {
            val prevFixturesFragment = PrevFixturesFragment()
            val args = Bundle()
            prevFixturesFragment.arguments = args
            return prevFixturesFragment
        }
    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView : View = inflater.inflate(R.layout.fragment_list_match,container,false)
        val rvListMatch = rootView.findViewById<RecyclerView>(R.id.rv_list_match)
        val swipeRefreshListMatch= rootView.findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_list_match)
        val viewModel = ViewModelProviders.of(this).get(PrevFixturesFragmentViewModel::class.java)

        rvListMatch.layoutManager = LinearLayoutManager(activity!!, LinearLayout.VERTICAL,false)
        val adapter = RVAdapter(activity!!)
        rvListMatch.adapter = adapter

        observeDataFeed(viewModel, adapter)
        println(TAG)

        swipeRefreshListMatch.setOnRefreshListener {
            observeDataFeed(viewModel, adapter)

            //itemsload is complete
        }
        return rootView
    }

    private fun observeDataFeed(viewModel:PrevFixturesFragmentViewModel, favoritesAdapter: RVAdapter){

        viewModel.getFixtureFeed().observe(this, Observer<FixtureFeed>{ fixtureFeed ->
            if (fixtureFeed != null) {
                progressBar.visibility = View.VISIBLE
                favoritesAdapter.setupListFixture(fixtureFeed.fixtures!!)
                progressBar.visibility = View.GONE
                Log.d(TAG, fixtureFeed.fixtures!!.get(0).date)
                println("VIEW : "+fixtureFeed.fixtures!![0].date)
            }
        })
    }


}