package com.example.dan.kadesubmission2.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class FixtureDetail {
    @SerializedName("idEvent")
    @Expose
    var idEvent: String? = null

    @SerializedName("dateEvent")
    @Expose
    var dateEvent: String? = null

    @SerializedName("strHomeTeam")
    @Expose
    var strHomeTeam: String? = null

    @SerializedName("strAwayTeam")
    @Expose
    var strAwayTeam: String? = null

    @SerializedName("intHomeScore")
    @Expose
    var homeScoreDetail: String? = null

    @SerializedName("intAwayScore")
    @Expose
    var awayScoreDetail: String? = null

    //region goals & shots
    @SerializedName("strHomeGoalDetails")
    @Expose
    var homeGoalDetails: String? = null

    @SerializedName("strAwayGoalDetails")
    @Expose
    var awayGoalDetails: String? = null

    @SerializedName("intHomeShots")
    @Expose
    var homeShots: String? = null

    @SerializedName("intAwayShots")
    @Expose
    var awayShots: String? = null
    //endregion

    //region lineup
    @SerializedName("strHomeLineupGoalkeeper")
    @Expose
    var homeGoalKeeper: String? = null

    @SerializedName("strAwayLineupGoalkeeper")
    @Expose
    var awayGoalKeeper: String? = null

    @SerializedName("strHomeLineupDefense")
    @Expose
    var homeDefense: String? = null

    @SerializedName("strAwayLineupDefense")
    @Expose
    var awayDefense: String? = null

    @SerializedName("strHomeLineupMidfield")
    @Expose
    var homeMidfield: String? = null

    @SerializedName("strAwayLineupMidfield")
    @Expose
    var awayMidfield: String? = null

    @SerializedName("strHomeLineupForward")
    @Expose
    var homeForward: String? = null

    @SerializedName("strAwayLineupForward")
    @Expose
    var awayForward: String? = null

    @SerializedName("strHomeLineupSubstitutes")
    @Expose
    var homeSubs: String? = null

    @SerializedName("strAwayLineupSubstitutes")
    @Expose
    var awaySubs: String? = null
}