package com.example.dan.kadesubmission2.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class TeamPlayersDetail{
    @SerializedName("idPlayer")
    @Expose
    open var playerID : String? = null

    @SerializedName("strPlayer")
    @Expose
    open var strPlayerName : String? = null

    @SerializedName("strPosition")
    @Expose
    open var strPosition : String? = null

    @SerializedName("strCutout")
    @Expose
    open var strCutout : String? = null

    @SerializedName("strHeight")
    @Expose
    open var strHeight : String? = null

    @SerializedName("strWeight")
    @Expose
    open var strWeight : String? = null

    @SerializedName("strFanart1")
    @Expose
    open var strFanart1 : String? = null

    @SerializedName("strDescriptionEN")
    @Expose
    open var strDescriptionEN : String? = null
}