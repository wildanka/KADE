package com.example.dan.kadesubmission2.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.databinding.FragmentFixturesBinding
import com.example.dan.kadesubmission2.view.adapter.ViewPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View.*
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.example.dan.kadesubmission2.model.entity.SearchEventFeeds
import com.example.dan.kadesubmission2.view.adapter.RVSearchFixturesAdapter
import com.example.dan.kadesubmission2.viewmodel.SearchFragmentViewModel


class FixturesFragment : Fragment() {
    private val TAG = "FragmentFixtures"
    lateinit var viewModel: SearchFragmentViewModel
    lateinit var adapter: RVSearchFixturesAdapter
    var binding: FragmentFixturesBinding? = null
    var mContext: Context? = activity
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContext = activity
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fixtures, container, false)
        viewModel = ViewModelProviders.of(this).get(SearchFragmentViewModel::class.java)

        binding!!.rvSearchMatch.layoutManager = LinearLayoutManager(activity!!, LinearLayout.VERTICAL, false)
        adapter = RVSearchFixturesAdapter(activity!!)
        binding!!.rvSearchMatch.adapter = adapter

        //setSupportActionBar
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(binding!!.tbFragmentFeatures)
        }
        createViewPager()

        return binding!!.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_search, menu)
        val searchItem = menu!!.findItem(R.id.action_search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as android.support.v7.widget.SearchView
            val editext = searchView.findViewById<EditText>(android.support.v7.appcompat.R.id.search_src_text)
            editext.hint = "Search Club Fixtures..."

            searchView.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    binding!!.vpFragmentFixtures.visibility = GONE
                    binding!!.flSearchResult.visibility = VISIBLE
                    observeSearchResult(query!!)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isNotEmpty()) {
                        val search = newText.toLowerCase()
                        Log.d("TAG", search)
//                        Toast.makeText(activity?.applicationContext, search, Toast.LENGTH_SHORT).show()
                    } else {
//                        Toast.makeText(activity?.applicationContext, "kosong", Toast.LENGTH_SHORT).show()
                        binding!!.vpFragmentFixtures.visibility = VISIBLE
                        binding!!.flSearchResult.visibility = INVISIBLE
                    }
                    return true
                }

            })
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun observeSearchResult(events: String) {
        viewModel.loadSearchFixtures(events)
        viewModel.getSearchFixtureFeed(events).observe(this, Observer<SearchEventFeeds> { fixtureFeed ->
            if (fixtureFeed != null) {
//                println("dateEvent : "+fixtureFeed.fixtures[0].dateEvent + " || " + fixtureFeed.fixtures[0].timeEvent + " || " + fixtureFeed.fixtures[0].homeClub)
//                println("strDate : "+fixtureFeed.fixtures[0].strDate + " || " + fixtureFeed.fixtures[0].timeEvent + " || " + fixtureFeed.fixtures[0].homeClub)
                adapter.setupListFixture(fixtureFeed.fixtures)
            }
        })
    }

    private fun createViewPager() {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(PrevFixturesFragment(), "Prev Match")
        adapter.addFragment(NextFixturesFragment(), "Next Match")
        binding!!.vpFragmentFixtures?.adapter = adapter
        binding!!.tlFixturesFragment.setupWithViewPager(binding!!.vpFragmentFixtures)

    }
}