package com.arun.data.repository

import com.arun.data.model.TwitterResponse
import io.reactivex.Observable
import retrofit2.http.Header
import retrofit2.http.QueryMap

interface ITwitterFeedRepository {

    fun getTwitterFeed(@Header("Authorization") authHeader: String, @QueryMap queryParams: Map<String, String>): Observable<List<TwitterResponse>>
}