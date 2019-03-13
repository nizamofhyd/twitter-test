package com.arun.domain.usecases

import com.arun.data.model.TwitterResponse
import com.arun.data.repository.ITwitterFeedRepository
import com.arun.domain.models.Tweet
import io.reactivex.Observable
import javax.inject.Inject

class TwitterUseCase @Inject constructor(private val twitterFeedRepository: ITwitterFeedRepository) {

    private val KEY_LIST_ID = "list_id"
    private val KEY_TWEET_MODE = "tweet_mode"
    private val KEY_ENTITIES = "include_entities"
    private val KEY_COUNT = "count"
    private val queryParams: Map<String, String>
    private val authBearerToken: String

    private val modelMapper: (List<TwitterResponse>) -> List<Tweet> = {
        val tweetsList = ArrayList<Tweet>()
        it.map {
            val tweet = Tweet(it.createdAt, it.fullText)
            tweetsList.add(tweet)
        }
        tweetsList
    }

    init {
        queryParams = HashMap()
        queryParams[KEY_LIST_ID] = "871746761387323394"
        queryParams[KEY_TWEET_MODE] = "extended"
        queryParams[KEY_ENTITIES] = "1"
        queryParams[KEY_COUNT] = "10"

        authBearerToken =
            "Bearer AAAAAAAAAAAAAAAAAAAAAF7w0wAAAAAAb6kdTQSU%2F5EmGAMD917iN7rZuVE%3D9ssQYqmHSgDTUgLDOW3k155qYVOxrGUaDSeOrW3IqvHeSoRYiu"
    }

    fun getTwitterFeed(): Observable<List<Tweet>> {
        return twitterFeedRepository.getTwitterFeed(authBearerToken, queryParams)
            .map { responseList -> modelMapper.invoke(responseList) }
    }
}