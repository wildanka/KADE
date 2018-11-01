package com.example.dan.kadesubmission2.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class FixtureList(s: String, s1: String, s2: String, s3: String, s4: String, s5: String, s6: String, s7: String) {

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
    var date: String? = null

    @SerializedName("strTime")
    @Expose
    var timeEvent: String? = null
}