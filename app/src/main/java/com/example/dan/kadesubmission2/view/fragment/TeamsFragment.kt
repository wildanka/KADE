package com.example.dan.kadesubmission2.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.*
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.databinding.FragmentTeamsBinding
import com.example.dan.kadesubmission2.model.entity.TeamLogoFeed
import com.example.dan.kadesubmission2.view.adapter.RVTeamsAdapter
import com.example.dan.kadesubmission2.viewmodel.TeamsFragmentViewModel
import kotlinx.android.synthetic.main.fragment_list_match.*

class TeamsFragment : Fragment() {
    private val TAG = "Fragment Teams"
    var binding: FragmentTeamsBinding? = null
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModel = ViewModelProviders.of(this).get(TeamsFragmentViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_teams,container,false)
        binding!!.contentFragTeams!!.rvListTeams.layoutManager = LinearLayoutManager(activity!!, LinearLayout.VERTICAL,false)
        //setSupportActionBar
        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).setSupportActionBar(binding!!.tbTeams)
        }
        val adapter = RVTeamsAdapter(activity!!)
        binding!!.contentFragTeams!!.rvListTeams.adapter = adapter

        observeDataFeed(viewModel, adapter, "4328")
        //adapter for spinner
        binding!!.contentFragTeams!!.spnLastFixtures.adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item , LEAGUE_NAME)

        //item listener for spinner
        binding!!.contentFragTeams!!.spnLastFixtures.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(activity, "You has selected "+LEAGUE_NAME[p2]+" with id "+LEAGUE_ID[p2],Toast.LENGTH_SHORT).show()
                SELECTED_ID_LEAGUE = LEAGUE_ID[p2]
                viewModel.loadTeams(SELECTED_ID_LEAGUE)
                observeDataFeed(viewModel,adapter,SELECTED_ID_LEAGUE)
            }
        }

        println(TAG)
        return binding!!.root
    }

    private fun observeDataFeed(viewModel: TeamsFragmentViewModel, teamsAdapter: RVTeamsAdapter, idLeague : String){
        Log.d("NOT YET EXECUTED","Observe")

        viewModel.getTeams(idLeague).observe(this, Observer<TeamLogoFeed>{ teamsFeed ->
            if (teamsFeed != null) {
                teamsAdapter.setupListTeams(teamsFeed.teamLogos!!)
                binding!!.contentFragTeams!!.progressBarTeams.visibility = View.GONE
                Log.d(TAG, teamsFeed.teamLogos!![0].idTeam)
                println("VIEW : "+teamsFeed.teamLogos!![0].teamName)
                Log.d("EXECUTED","Observe")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_search,menu)
        val searchItem = menu!!.findItem(R.id.action_search)
        if(searchItem != null){
            val searchView = searchItem.actionView as SearchView
            val editext = searchView.findViewById<EditText>(android.support.v7.appcompat.R.id.search_src_text)
            editext.hint = "Search Clubs..."

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
               /*     displayList.clear()
                    if(newText!!.isNotEmpty()){
                        val search = newText.toLowerCase()
                        countries.forEach {
                            if(it.toLowerCase().contains(search)){
                                displayList.add(it)
                            }
                        }
                    }else{
                        displayList.addAll(countries)
                    }
                    country_list.adapter.notifyDataSetChanged()*/
                    return true
                }

            })
        }
        super.onCreateOptionsMenu(menu, inflater)
    }


    private fun setSpinnerLeague(viewModel: TeamsFragmentViewModel, teamsAdapter: RVTeamsAdapter){

        //adapter for spinner
        binding!!.contentFragTeams!!.spnLastFixtures.adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item , LEAGUE_NAME)

        //item listener for spinner
        binding!!.contentFragTeams!!.spnLastFixtures.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(activity, "You has selected "+LEAGUE_NAME[p2]+" with id "+LEAGUE_ID[p2],Toast.LENGTH_SHORT).show()
                SELECTED_ID_LEAGUE = LEAGUE_ID[p2]
                viewModel.loadTeams(SELECTED_ID_LEAGUE)
//                observeDataFeed(viewModel,teamsAdapter,SELECTED_ID_LEAGUE)
            }
        }

        Log.d("CEK_ID", SELECTED_ID_LEAGUE)
        observeDataFeed(viewModel, teamsAdapter, SELECTED_ID_LEAGUE)

    }
}