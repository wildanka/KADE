package com.example.dan.kadesubmission2.view.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.databinding.FragmentMenuFavoritesBinding
import com.example.dan.kadesubmission2.view.adapter.ViewPagerAdapter

class FavoritesMenuFragment : Fragment(){
    var binding : FragmentMenuFavoritesBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu_favorites, container, false)
        createViewPager()
        return binding!!.root
    }

    private fun createViewPager(){
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(FavoritesFragment(), "Match")
        adapter.addFragment(FavoritesTeamsFragment(), "Teams")
        binding!!.vpFragmentMenuFavorites.adapter = adapter
        binding!!.tlFragmentMenuFavorites.setupWithViewPager(binding!!.vpFragmentMenuFavorites)
    }
}