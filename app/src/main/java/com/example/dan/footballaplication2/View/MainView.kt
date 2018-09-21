package com.example.dan.footballaplication2.View

import com.example.dan.footballaplication2.Model.Team

interface MainView{
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}