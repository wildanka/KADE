package com.example.dan.kadesubmission2.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class FixtureDetailsFeed{
    @SerializedName("events")
    @Expose
    var fixtureDetails: List<FixtureDetail>? = null
}