package com.example.dan.kadesubmission2.view

import android.databinding.DataBindingUtil
import android.databinding.adapters.ViewGroupBindingAdapter.setListener
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.example.dan.kadesubmission2.R
import com.example.dan.kadesubmission2.databinding.ActivityMainBinding
import com.example.dan.kadesubmission2.view.fragment.*

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "Fragment PREV FIXTURES"
    var binding : ActivityMainBinding? = null


//    fun setSearchViewListener(mSearchViewListener: SearchViewListener){
//        this.mSearchViewListener = mSearchViewListener
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        super.onCreate(savedInstanceState)
//        setSupportActionBar(tb_activity_main)
        println(TAG)

        binding!!.tambah!!.bottomNavbar.setOnNavigationItemSelectedListener {item ->
            when(item.itemId){
                R.id.navigation_fixtures -> {
                    loadPrevFragment(savedInstanceState)
                }
                R.id.navigation_teams ->{
                    loadNextFragment(savedInstanceState)
                }
                R.id.navigation_favorites ->{
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }

        binding!!.tambah!!.bottomNavbar.selectedItemId = R.id.navigation_fixtures

//        addFragment(fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu_search,menu)
//        val searchItem = menu.findItem(R.id.action_search)
//        if(searchItem != null){
//            val searchView = searchItem.actionView as SearchView
//            val editext = searchView.findViewById<EditText>(android.support.v7.appcompat.R.id.search_src_text)
//            editext.hint = "Search here..."
//
//            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    if(query!!.isNotEmpty()){
//                        val search = query.toLowerCase()
//                        mSearchViewListener.myAction(search)
//                        Log.d("TAG",search)
//                        Toast.makeText(applicationContext,search,Toast.LENGTH_SHORT).show()
//
//                    }else{
//                        Toast.makeText(applicationContext,"kosong",Toast.LENGTH_SHORT).show()
////                        displayList.addAll(countries)
//                    }
//                    return true
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
////                    displayList.clear()
//                    if(newText!!.isNotEmpty()){
//                        val search = newText.toLowerCase()
////                        mSearchViewListener.myAction(search)
//                        Log.d("TAG",search)
//                        Toast.makeText(applicationContext,search,Toast.LENGTH_SHORT).show()
//                    }else{
//                        Toast.makeText(applicationContext,"kosong",Toast.LENGTH_SHORT).show()
////                        displayList.addAll(countries)
//                    }
////                    country_list.adapter.notifyDataSetChanged()
//                    return true
//                }
//
//            })
//        }
        return super.onCreateOptionsMenu(menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
////        return when (item.itemId) {
////            R.id.action_settings -> true
////            else -> super.onOptionsItemSelected(item)
////        }
//    }


    private fun loadPrevFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {

            supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.design_bottom_sheet_slide_in,R.anim.design_bottom_sheet_slide_out)
                    .replace(R.id.fl_main_container,FixturesFragment(),FixturesFragment::class.java.simpleName)
                    .commit()
        }
    }
    private fun loadNextFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.design_bottom_sheet_slide_in,R.anim.design_bottom_sheet_slide_out)
                    .replace(R.id.fl_main_container,TeamsFragment(),TeamsFragment::class.java.simpleName)
                    .commit()
        }
    }
    private fun loadFavoritesFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.design_bottom_sheet_slide_in,R.anim.design_bottom_sheet_slide_out)
//                    .replace(R.id.fl_main_container,TestFragment(),TestFragment::class.java.simpleName)
                    .replace(R.id.fl_main_container,FavoritesMenuFragment(),FavoritesMenuFragment::class.java.simpleName)
                    .commit()
        }

    }


}