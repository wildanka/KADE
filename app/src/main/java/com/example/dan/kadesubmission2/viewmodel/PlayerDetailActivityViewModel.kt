package com.example.dan.kadesubmission2.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.dan.kadesubmission2.model.entity.TeamPlayersDetail

class PlayerDetailActivityViewModel : ViewModel(){
    private lateinit var playersDetailsData : MutableLiveData<TeamPlayersDetail>
}