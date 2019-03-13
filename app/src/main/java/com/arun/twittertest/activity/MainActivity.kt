package com.arun.twittertest.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.arun.domain.models.Tweet
import com.arun.twittertest.R
import com.arun.twittertest.adapters.TwitterListAdapter
import com.arun.twittertest.util.Status
import com.arun.twittertest.viewmodels.TwitterFeedViewModel
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var twitterFeedViewModel: TwitterFeedViewModel

    private lateinit var twitterListAdapter: TwitterListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        twitterFeedViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(TwitterFeedViewModel::class.java)
        bind()
        fetchData()
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    fun bind() {
        twitterRecyclerView.layoutManager = LinearLayoutManager(this)
        twitterListAdapter = TwitterListAdapter(emptyList())
        twitterRecyclerView.adapter = twitterListAdapter
        twitterFeedViewModel.tweetsLiveData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.model as List<Tweet>
                    twitterListAdapter.updateTweets(it.model)
                }
                Status.ERROR -> {
                    val dialog = AlertDialog.Builder(this)
                        .setTitle(R.string.dialog_title)
                        .setMessage(R.string.dialog_error_msg)
                        .setPositiveButton(R.string.dialog_positive) { dialog, which -> dialog.dismiss() }
                        .show()

                }
            }
        })
    }

    fun fetchData() {
        twitterFeedViewModel.getTwitterFeed()
    }
}
