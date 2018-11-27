package com.example.dan.kadesubmission2.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class SearchEventFeeds(@SerializedName("event")
                       @Expose
                       open var fixtures: List<FixtureList>)