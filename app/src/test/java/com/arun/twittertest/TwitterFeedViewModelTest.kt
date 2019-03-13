package com.arun.twittertest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.arun.domain.models.Tweet
import com.arun.domain.usecases.TwitterUseCase
import com.arun.twittertest.rules.RxImmediateSchedulerRule
import com.arun.twittertest.util.LiveModel
import com.arun.twittertest.util.Status
import com.arun.twittertest.viewmodels.TwitterFeedViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TwitterFeedViewModelTest {

    @Rule
    @JvmField
    val schedulersRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    lateinit var twitterFeedViewModel: TwitterFeedViewModel

    lateinit var listOfTweets: ArrayList<Tweet>

    private val mockUseCase = mock<TwitterUseCase> {}

    @Before
    fun setUp() {
        twitterFeedViewModel = TwitterFeedViewModel(mockUseCase)
        listOfTweets = ArrayList()
        listOfTweets.add(Tweet("Feb 1 2019", description = "Tweet 1"))
        listOfTweets.add(Tweet("Jan 20 2019", description = "Tweet 2"))
    }

    @Test
    fun getTwitterFeedWithError() {

        whenever(mockUseCase.getTwitterFeed()).thenReturn(Observable.error(Exception()))

        twitterFeedViewModel.getTwitterFeed()
        twitterFeedViewModel.tweetsLiveData.value as LiveModel<List<Tweet>>
        val liveModel = twitterFeedViewModel.tweetsLiveData.value

        assertEquals(liveModel?.status, Status.ERROR)
    }

    @Test
    fun getTwitterFeedWithSuccess() {
        whenever(mockUseCase.getTwitterFeed()).thenReturn(Observable.just(listOfTweets))

        twitterFeedViewModel.getTwitterFeed()
        twitterFeedViewModel.tweetsLiveData.value as LiveModel<List<Tweet>>
        val liveModel = twitterFeedViewModel.tweetsLiveData.value

        assertEquals(liveModel?.status, Status.SUCCESS)
        assertEquals(liveModel?.model?.size, 2)
    }
}