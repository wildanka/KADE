package com.example.dan.kadesubmission2.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.databinding.FragmentTeamOverviewBinding
import com.example.dan.kadesubmission2.viewmodel.TeamDetailOverviewFragmentViewModel

class TeamDetailsOverviewFragment : Fragment(){
    private var binding : FragmentTeamOverviewBinding? = null
//    private val idClub = arguments?.getString("ID_CLUB")
    companion object {
        fun newInstance(idClub: String): TeamDetailsOverviewFragment {
            val args = Bundle()
            args.putString("ID_CLUB",idClub)
            val fragment = TeamDetailsOverviewFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val idClub = arguments?.getString("ID_CLUB")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_team_overview, container, false)
        val viewModel = ViewModelProviders.of(this).get(TeamDetailOverviewFragmentViewModel::class.java)
        println("NULL EUY")
        if (idClub != null) {
            observeTeamOverview(viewModel, idClub)
            println("NOT NULL")
        }
        return binding!!.root
    }

    private fun observeTeamOverview(viewModel : TeamDetailOverviewFragmentViewModel, idClub : String){
        viewModel.getOverview(idClub).observe(this, Observer<String>{overviews ->
            if (overviews != null) {
                binding!!.tvOverviewDetails.text = overviews
                Log.d("DetOverviews", overviews)
            }

        })
    }

}
