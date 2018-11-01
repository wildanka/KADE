package com.example.dan.kadesubmission2.view.fragment

import android.app.SearchManager
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.databinding.FragmentTestBinding
import com.example.dan.kadesubmission2.view.adapter.RVTeamsAdapter
import com.example.dan.kadesubmission2.viewmodel.TeamsFragmentViewModel
import android.databinding.adapters.SearchViewBindingAdapter.setOnQueryTextListener
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_fixtures.*


class TestFragment : Fragment() {
    private val TAG = "Fragment Teams"
    var binding: FragmentTestBinding? = null
    private var searchView: SearchView? = null
    private var queryTextListener: SearchView.OnQueryTextListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test, container, false)
         //setSupportActionBar
        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).setSupportActionBar(binding!!.tbTesFragment)
        }

        println(TAG)
        return binding!!.root
    }

    private fun setSpinnerLeague(viewModel: TeamsFragmentViewModel, teamsAdapter: RVTeamsAdapter) {

/*
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
*/

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_search,menu)
        val searchItem = menu!!.findItem(R.id.action_search)
        if(searchItem != null){
            val searchView = searchItem.actionView as SearchView
            val editext = searchView.findViewById<EditText>(android.support.v7.appcompat.R.id.search_src_text)
            editext.hint = "Search here..."

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

//                    displayList.clear()
//                    if(newText!!.isNotEmpty()){
//                        val search = newText.toLowerCase()
//                        countries.forEach {
//                            if(it.toLowerCase().contains(search)){
//                                displayList.add(it)
//                            }
//                        }
//                    }else{
//                        displayList.addAll(countries)
//                    }
//                    country_list.adapter.notifyDataSetChanged()
                    return true
                }

            })
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        val menuItem = menu?.findItem(R.id.action_search)
        val search = menuItem?.actionView as SearchView

        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            R.id.action_search ->
                // Not implemented here
                return false
            else -> {
            }
        }
        searchView?.setOnQueryTextListener(queryTextListener)
        return super.onOptionsItemSelected(item)
    }
}