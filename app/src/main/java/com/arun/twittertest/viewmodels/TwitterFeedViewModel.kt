package com.arun.twittertest.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arun.domain.models.Tweet
import com.arun.domain.usecases.TwitterUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TwitterFeedViewModel @Inject constructor(private val twitterUseCase: TwitterUseCase) : ViewModel() {

    val tweetsLiveData = MutableLiveData<List<Tweet>>()

    fun getTwitterFeed() {
        twitterUseCase.getTwitterFeed()
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                tweetsLiveData.postValue(it)
            }

    }
}