package com.example.dan.kadesubmission2.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class TeamLogoFeed{
    @SerializedName("teams")
    @Expose
    open var teamLogos: List<TeamLogo>? = null
}