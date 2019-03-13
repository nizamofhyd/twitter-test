package com.arun.data

import com.arun.data.model.TwitterResponse
import com.arun.data.repository.ITwitterFeedRepository
import com.arun.data.repository.TwitterFeedRepository
import com.arun.data.service.TwitterService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyMap
import org.mockito.ArgumentMatchers.anyString

class TwitterRepositoryTest {


    lateinit var twitterFeedRepository: ITwitterFeedRepository

    private val mockTwitterService = mock<TwitterService> {}

    private val mockList = mock<List<TwitterResponse>> {}

    @Before
    fun setUp() {
        twitterFeedRepository = TwitterFeedRepository(mockTwitterService)
    }

    @Test
    fun getTwitterFeeds() {

        whenever(mockTwitterService.getTwitterFeed(anyString(), anyMap())).thenReturn(Observable.just(mockList))

        val testObserver: TestObserver<List<TwitterResponse>> =
            twitterFeedRepository.getTwitterFeed(anyString(), anyMap()).test()

        testObserver.assertNoErrors()
        testObserver.assertValue(mockList)
    }
}