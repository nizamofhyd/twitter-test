package com.arun.data.service

import com.arun.data.model.TwitterResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

interface TwitterService {

    @GET("1.1/lists/statuses.json?list_id=871746761387323394&tweet_mode=extended&include_entities=1&count=10")
    fun getTwitterFeed(@Header("Authorization") authHeader: String, @QueryMap queryParams: Map<String, String>): Observable<List<TwitterResponse>>
}