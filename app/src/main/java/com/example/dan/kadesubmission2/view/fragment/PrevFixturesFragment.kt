package com.example.dan.kadesubmission2.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.databinding.FragmentListMatchBinding
import com.example.dan.kadesubmission2.model.entity.FixtureFeed
import com.example.dan.kadesubmission2.view.adapter.RVFixturesAdapter
import com.example.dan.kadesubmission2.view.adapter.RVNextFixturesAdapter
import com.example.dan.kadesubmission2.view.adapter.RVPrevFixturesAdapter
import com.example.dan.kadesubmission2.viewmodel.PrevFixturesFragmentViewModel
import kotlinx.android.synthetic.main.fragment_list_match.*
import android.databinding.adapters.SearchViewBindingAdapter.setOnQueryTextListener
import android.support.v4.view.MenuItemCompat.getActionView
import android.content.Context.SEARCH_SERVICE
import android.app.SearchManager
import android.content.Context
import android.databinding.adapters.ViewGroupBindingAdapter.setListener
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.widget.*
import com.example.dan.kadesubmission2.view.MainActivity


class PrevFixturesFragment : Fragment(){
    lateinit var viewModel : PrevFixturesFragmentViewModel
    lateinit var adapter : RVPrevFixturesAdapter

    private val TAG = "Fragment PREV FIXTURES"
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
    /*companion object {
        fun newInstance(): PrevFixturesFragment {
            val prevFixturesFragment = PrevFixturesFragment()
            val args = Bundle()
            prevFixturesFragment.arguments = args
            return prevFixturesFragment
        }
    }*/


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(PrevFixturesFragmentViewModel::class.java)
        adapter = RVPrevFixturesAdapter(activity!!)
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_match, container, false)


        /*val rootView : View = inflater.inflate(R.layout.fragment_list_match,container,false)
        val rvListMatch = rootView.findViewById<RecyclerView>(R.id.rv_list_match)
        val swipeRefreshListMatch= rootView.findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_list_match)*/
        viewModel = ViewModelProviders.of(this).get(PrevFixturesFragmentViewModel::class.java)

        binding!!.rvListMatch.layoutManager = LinearLayoutManager(activity!!, LinearLayout.VERTICAL, false)
        adapter = RVPrevFixturesAdapter(activity!!)
        binding!!.rvListMatch.adapter = adapter

        //initial load
        observeDataFeed(viewModel, adapter, "4328")
        println(TAG)

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
        return binding!!.root
    }

    private fun observeDataFeed(viewModel: PrevFixturesFragmentViewModel, favoritesAdapter: RVPrevFixturesAdapter, idLeague: String) {
        viewModel.getFixtureFeed(idLeague).observe(this, Observer<FixtureFeed> { fixtureFeed ->
            if (fixtureFeed != null) {
                binding!!.progressBar.visibility = View.VISIBLE
                favoritesAdapter.setupListFixture(fixtureFeed.fixtures!!)
                binding!!.progressBar.visibility = View.GONE
                Log.d(TAG, fixtureFeed.fixtures!!.get(0).dateEvent)
                println("VIEW : "+fixtureFeed.fixtures!![0].dateEvent+" ||| "+fixtureFeed.fixtures!![0].timeEvent)
            }
        })
    }

}