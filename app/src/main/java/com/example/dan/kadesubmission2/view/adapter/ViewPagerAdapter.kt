package com.example.dan.kadesubmission2.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val mFragment = ArrayList<Fragment>()
    private val mFragmentTitle = ArrayList<String>()


    override fun getItem(position: Int): Fragment {
        return mFragment[position]
    }

    override fun getCount(): Int {
        return mFragmentTitle.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitle[position]
    }

    fun addFragment(fragment : Fragment, title: String){
        mFragment.add(fragment)
        mFragmentTitle.add(title)
    }
}