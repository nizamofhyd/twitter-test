package com.arun.data.repository

import com.arun.data.model.TwitterResponse
import com.arun.data.service.TwitterService
import io.reactivex.Observable
import javax.inject.Inject

class TwitterFeedRepository @Inject constructor(val twitterService: TwitterService) : ITwitterFeedRepository {

    override fun getTwitterFeed(
        authHeader: String,
        queryParams: Map<String, String>
    ): Observable<List<TwitterResponse>> {
        return twitterService.getTwitterFeed(authHeader, queryParams)
    }
}