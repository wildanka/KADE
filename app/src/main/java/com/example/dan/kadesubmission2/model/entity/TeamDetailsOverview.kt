package com.example.dan.kadesubmission2.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class TeamDetailsOverview {
    @SerializedName("strDescriptionEN")
    @Expose
    var strDescriptionEN : String? = null
}