package com.arun.domain

import com.arun.data.model.TwitterResponse
import com.arun.data.repository.ITwitterFeedRepository
import com.arun.data.repository.TwitterFeedRepository
import com.arun.domain.models.Tweet
import com.arun.domain.usecases.TwitterUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyMap
import org.mockito.ArgumentMatchers.anyString

class TwitterUseCaseTest {

    val mockTwitterFeedRepository = mock<TwitterFeedRepository> {}

    lateinit var listOfTwitterResponse: ArrayList<TwitterResponse>
    lateinit var listOfTweets: ArrayList<Tweet>
    lateinit var twitterUseCase: TwitterUseCase

    @Before
    fun setUp() {
        listOfTwitterResponse = ArrayList()
        listOfTwitterResponse.add(TwitterResponse("Response text 1", "Fri Mar 08 14:05:02 +0000 2019"))
        listOfTwitterResponse.add(TwitterResponse("Response text 2", "Fri Mar 08 13:49:02 +0000 2019"))
        listOfTweets = ArrayList()
        listOfTweets.add(Tweet(description = "Response text 1", date = "Fri Mar 08 14:05:02 +0000 2019"))
        listOfTweets.add(Tweet(description = "Response text 2", date = "Fri Mar 08 13:49:02 +0000 2019"))
        twitterUseCase = TwitterUseCase(mockTwitterFeedRepository)
    }

    @Test
    fun getTwitterFeed() {

        whenever(mockTwitterFeedRepository.getTwitterFeed(anyString(), anyMap())).thenReturn(
            Observable.just(
                listOfTwitterResponse
            )
        )

        val testObserver = twitterUseCase.getTwitterFeed().test()

        testObserver.assertNoErrors()
        testObserver.assertValue(listOfTweets)
    }
}