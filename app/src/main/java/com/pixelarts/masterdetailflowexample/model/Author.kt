package com.pixelarts.masterdetailflowexample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Author(
    val name: String,
    val headshot: String,
    var twitter: String
):Parcelable