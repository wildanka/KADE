package com.example.dan.kadesubmission2.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

open class FixtureFeed {
    @SerializedName("events")
    @Expose
    open var fixtures: ArrayList<FixtureList>? = null
}