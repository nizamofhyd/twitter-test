package com.arun.twittertest.activity

import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.arun.domain.models.Tweet
import com.arun.twittertest.R
import com.arun.twittertest.adapters.TwitterListAdapter
import com.arun.twittertest.di.Injectable
import com.arun.twittertest.util.BackgroundIdleResourceListener
import com.arun.twittertest.util.Status
import com.arun.twittertest.viewmodels.TwitterFeedViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var twitterFeedViewModel: TwitterFeedViewModel

    private lateinit var twitterListAdapter: TwitterListAdapter

    //Used for espresso idle resource listener
    var backgroundIdleResourceListener: BackgroundIdleResourceListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        twitterFeedViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(TwitterFeedViewModel::class.java)
        bind()
        fetchData()
    }

    fun bind() {
        twitterRecyclerView.layoutManager = LinearLayoutManager(this)
        twitterListAdapter = TwitterListAdapter(emptyList())
        twitterRecyclerView.adapter = twitterListAdapter
        twitterFeedViewModel.tweetsLiveData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.model as List<Tweet>
                    twitterListAdapter.updateTweets(it.model)
                    backgroundIdleResourceListener?.end()
                }
                Status.ERROR -> {
                    val dialog = AlertDialog.Builder(this)
                        .setTitle(R.string.dialog_title)
                        .setMessage(R.string.dialog_error_msg)
                        .setPositiveButton(R.string.dialog_positive) { dialog, which ->
                            run {
                                dialog.dismiss()
                                finish()
                            }
                        }
                        .show()

                }
            }
        })
    }

    fun fetchData() {
        twitterFeedViewModel.getTwitterFeed()
    }

    @VisibleForTesting
    fun setIdle() {
        backgroundIdleResourceListener?.start()
    }
}
