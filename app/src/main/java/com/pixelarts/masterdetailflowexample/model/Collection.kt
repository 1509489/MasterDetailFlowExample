package com.pixelarts.masterdetailflowexample.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Collection(
    val id : Int,
    @SerializedName("website-url") val websiteUrl: String,
    val headline: String,
    val description: String,
    @SerializedName("article-body") val articleBody: String,
    var ratings: Int,
    @SerializedName("picture-url") val pictureUrl: String,
    @SerializedName("video-url") val videoUrl: String,
    val actors: List<String>,
    val director: String,
    val genre: List<String>,
    val synopsis: String,
    @SerializedName("release-date") val releaseDate: String,
    val duration: String,
    @SerializedName("published-date") val publishedDate: String,
    val author: Author
):Parcelable