package com.example.dan.belajarkotlin.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by DAN on 8/31/2018.
 */

@Parcelize
data class Club(val clubName: String?, val clubLogo: Int?, val clubDesc: String?):Parcelable