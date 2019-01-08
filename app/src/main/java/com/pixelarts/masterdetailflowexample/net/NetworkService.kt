package com.pixelarts.masterdetailflowexample.net

import com.pixelarts.masterdetailflowexample.model.Response
import io.reactivex.Single
import retrofit2.http.GET

interface NetworkService {
    @GET("tmgmobilepub/articles.json")
    fun getArticles(): Single<Response>
}