package com.example.dan.kadesubmission2.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class TeamLogo{
    @SerializedName("idTeam")
    @Expose
    var idTeam: String? = null

    @SerializedName("strTeamBadge")
    @Expose
    var linkClubLogo: String? = null
}