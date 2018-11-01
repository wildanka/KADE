package com.example.dan.kadesubmission2.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class TeamPlayers{
    @SerializedName("player")
    @Expose
    open var playerList : List<TeamPlayersDetail>? = null
}