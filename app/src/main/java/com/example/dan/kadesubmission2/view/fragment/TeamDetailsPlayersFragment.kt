package com.example.dan.kadesubmission2.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.databinding.FragmentPlayerListBinding
import com.example.dan.kadesubmission2.model.entity.TeamPlayers
import com.example.dan.kadesubmission2.view.adapter.RVTeamsPlayerAdapter
import com.example.dan.kadesubmission2.viewmodel.TeamPlayersFragmentViewModel

class TeamDetailsPlayersFragment : Fragment(){
    private var binding : FragmentPlayerListBinding? = null
    companion object {
        fun newInstance(idClub: String): TeamDetailsPlayersFragment {
            val args = Bundle()
            args.putString("ID_CLUB",idClub)
            val fragment = TeamDetailsPlayersFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player_list, container, false)
        val idClub = arguments?.getString("ID_CLUB")
        val viewModel = ViewModelProviders.of(this).get(TeamPlayersFragmentViewModel::class.java)

        val adapter = RVTeamsPlayerAdapter(activity!!)
        binding!!.rvListPlayer.layoutManager = LinearLayoutManager(activity!!, LinearLayout.VERTICAL, false)
        binding!!.rvListPlayer.adapter = adapter
        if (idClub != null) {
            observePlayerList(viewModel, idClub, adapter)
            println("id club player is not null")
        }
        return binding!!.root
    }

    private fun observePlayerList(viewModel : TeamPlayersFragmentViewModel, idClub: String, adapter : RVTeamsPlayerAdapter){
        viewModel.getPlayerList(idClub).observe(this, Observer<TeamPlayers>{players ->
            if (players != null) {
                Log.d("playerObs", "Not null")
                adapter.setupListTeams(players.playerList!!)
                println("VIEW : "+players.playerList!![0].strPlayerName+" => "+players.playerList!![0].strPosition)
            }
            Log.d("playerObs", "null")
        })
    }
}