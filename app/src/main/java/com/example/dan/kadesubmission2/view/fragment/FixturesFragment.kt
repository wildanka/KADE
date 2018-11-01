 package com.example.dan.kadesubmission2.view.fragment

import android.app.SearchManager
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.view.*
import android.widget.SearchView
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.databinding.FragmentFixturesBinding
import com.example.dan.kadesubmission2.view.MainActivity
import com.example.dan.kadesubmission2.view.adapter.ViewPagerAdapter
import android.support.v4.view.MenuItemCompat.getActionView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_fixtures.*


 class FixturesFragment : Fragment() {
    var binding: FragmentFixturesBinding? = null
    var mContext : Context? = activity
    private var menuItem: Menu? = null

   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContext = activity
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fixtures, container, false)
        //setSupportActionBar
        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).setSupportActionBar(binding!!.tbFragmentFeatures)
        }
        createViewPager()

//        setHasOptionsMenu(true)
        return binding!!.root
    }


     override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
         inflater!!.inflate(R.menu.menu_search,menu)
         val searchItem = menu!!.findItem(R.id.action_search)
         if(searchItem != null){
             val searchView = searchItem.actionView as android.support.v7.widget.SearchView
             val editext = searchView.findViewById<EditText>(android.support.v7.appcompat.R.id.search_src_text)
             editext.hint = "Search Club Fixtures..."

             searchView.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener{
                 override fun onQueryTextSubmit(query: String?): Boolean {
                     return true
                 }

                 override fun onQueryTextChange(newText: String?): Boolean {
                    /*displayList.clear()
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

//     region onCreateOptionsMenu
//    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
//        inflater!!.inflate(R.menu.menu_favorites, menu)
//        menuItem = menu
//
//
//        /*val searchItem : MenuItem = menu!!.findItem(R.id.search)
//        val searchView = SearchView(activity)
//        searchView.queryHint = "Search Fixtures"
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(p0: String?): Boolean {
//                return true
//            }
//
//        })*/
////        searchItem.actionView = searchView
//    }
//     endregion onCreateOptionsMenu

    private fun createViewPager() {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(PrevFixturesFragment(), "Prev Match")
        adapter.addFragment(NextFixturesFragment(), "Next Match")
        binding!!.vpFragmentFixtures?.adapter = adapter
        binding!!.tlFixturesFragment.setupWithViewPager(binding!!.vpFragmentFixtures)

        /*binding!!.contentFragmentFixtures?.vpFragmentFixtures?.adapter = adapter
        binding!!.tlFixturesFragment.setupWithViewPager(binding!!.contentFragmentFi)xtures?.vpFragmentFixtures*/
    }
}