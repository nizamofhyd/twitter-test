package com.arun.twittertest.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arun.domain.models.Tweet
import com.arun.domain.usecases.TwitterUseCase
import com.arun.twittertest.util.LiveModel
import com.arun.twittertest.util.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TwitterFeedViewModel @Inject constructor(private val twitterUseCase: TwitterUseCase) : ViewModel() {

    val tweetsLiveData = MutableLiveData<LiveModel<List<Tweet>>>()
    lateinit var liveModel: LiveModel<List<Tweet>>

    fun getTwitterFeed() {
        twitterUseCase.getTwitterFeed()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { result ->
                    liveModel = LiveModel(result, Status.SUCCESS, null)
                    tweetsLiveData.postValue(liveModel)
                },
                { error ->
                    println("Error occured during tweets fetch : $error")
                    liveModel = LiveModel(null, Status.ERROR, error.message)
                    tweetsLiveData.postValue(liveModel)

                },
                { println("Tweets received successfully !!") })

    }
}