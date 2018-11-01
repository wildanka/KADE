package com.example.dan.kadesubmission2.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.databinding.FragmentListMatchBinding
import com.example.dan.kadesubmission2.model.entity.FixtureFeed
import com.example.dan.kadesubmission2.view.adapter.RVNextFixturesAdapter
import com.example.dan.kadesubmission2.viewmodel.NextFixturesFragmentViewModel
import kotlinx.android.synthetic.main.fragment_list_match.*

class NextFixturesFragment : Fragment(){
    private val TAG = "Fragment NEXT FIXTURES"
    var binding: FragmentListMatchBinding? = null
    var SELECTED_ID_LEAGUE = ""
    val LEAGUE_ID = arrayOf(
            "4328",
            "4335",
            "4331",
            "4334",
            "4332",
            "4344",
            "4337"
    )
    val LEAGUE_NAME = arrayOf(
            "English Premier League",
            "Spanish La Liga",
            "Germany Bundesliga",
            "French Ligue 1",
            "Italian Serie A",
            "Portuguese Primeira Liga",
            "Netherlands Eredivisie"
    ) //string array
    /**
     * Initialize newInstance for passing parameters
     */
/*
    companion object {
        fun newInstance(): NextFixturesFragment {
            val nextFixturesFragment = NextFixturesFragment()
            val args = Bundle()
            nextFixturesFragment.arguments = args
            return nextFixturesFragment
        }
    }
*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_match, container, false)
        /*val rootView : View = inflater.inflate(R.layout.fragment_list_match,container,false)
        val rvListMatch = rootView.findViewById<RecyclerView>(R.id.rv_list_match)*/

        val viewModel = ViewModelProviders.of(this).get(NextFixturesFragmentViewModel::class.java)
        binding!!.rvListMatch.layoutManager = LinearLayoutManager(activity!!, LinearLayout.VERTICAL, false)
        val adapter = RVNextFixturesAdapter(activity!!)
        binding!!.rvListMatch.adapter = adapter

        //initial load
        observeDataFeed(viewModel, adapter, "4328")

        binding!!.swipeRefreshListMatch.setOnRefreshListener {
            observeDataFeed(viewModel, adapter, "4328")
            //itemsload is complete
        }

        //adapter for spinner
        binding!!.spnLastFixtures.adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, LEAGUE_NAME)

        //item listener for spinner
        binding!!.spnLastFixtures.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(activity, "You has selected " + LEAGUE_NAME[p2] + " with id " + LEAGUE_ID[p2], Toast.LENGTH_SHORT).show()
                SELECTED_ID_LEAGUE = LEAGUE_ID[p2]
                viewModel.loadFixtures(SELECTED_ID_LEAGUE)
                observeDataFeed(viewModel, adapter, SELECTED_ID_LEAGUE)
            }
        }
        println(TAG)
        return binding!!.root
    }

    private fun observeDataFeed(viewModel:NextFixturesFragmentViewModel, favoritesAdapter: RVNextFixturesAdapter, idLeague : String){
        viewModel.getFixtureFeed(idLeague).observe(this, Observer<FixtureFeed>{ fixtureFeed ->
            if (fixtureFeed != null) {
                binding!!.progressBar.visibility = View.VISIBLE
                favoritesAdapter.setupListFixture(fixtureFeed.fixtures!!)
                binding!!.progressBar.visibility = View.GONE
                Log.d(TAG, fixtureFeed.fixtures!!.get(0).date)
                println("VIEW : "+fixtureFeed.fixtures!![0].date)
            }
        })
    }



}