package com.example.dan.kadesubmission2.view.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.dan.kadesubmission2.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentMenuPrevMatch.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FragmentMenuPrevMatch.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FragmentMenuPrevMatch : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_menu_prev_match, container, false)
    }


}