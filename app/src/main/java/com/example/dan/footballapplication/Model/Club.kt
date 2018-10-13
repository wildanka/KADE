package com.example.dan.footballapplication.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Club(val clubName: String?, val clubLogo: Int?, val clubDesc: String?): Parcelable