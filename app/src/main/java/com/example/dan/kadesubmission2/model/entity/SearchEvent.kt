package com.example.dan.kadesubmission2.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

open class SearchEvent{
    @SerializedName("idEvent")
    @Expose
    var idEvent: String? = null

    @SerializedName("idHomeTeam")
    @Expose
    var idHomeTeam: String? = null

    @SerializedName("idAwayTeam")
    @Expose
    var idAwayTeam: String? = null

    @SerializedName("strHomeTeam")
    @Expose
    var homeClub: String? = null

    @SerializedName("intHomeScore")
    @Expose
    var homeClubScore: String? = null

    @SerializedName("strAwayTeam")
    @Expose
    var awayClub: String? = null

    @SerializedName("intAwayScore")
    @Expose
    var awayClubScore: String? = null

    @SerializedName("dateEvent")
    @Expose
    var dateEvent: String? = null

    @SerializedName("strDate")
    @Expose
    var strDate: String? = null

    @SerializedName("strTime")
    @Expose
    var timeEvent: String? = null
}