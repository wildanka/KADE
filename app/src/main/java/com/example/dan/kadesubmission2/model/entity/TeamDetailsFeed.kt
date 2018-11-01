package com.example.dan.kadesubmission2.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class TeamDetailsFeed(){
    @SerializedName("teams")
    @Expose
    var teamOverviews : List<TeamDetailsOverview>? = null
}