package com.arun.twittertest.adapters

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arun.domain.models.Tweet
import com.arun.twittertest.R

class TwitterListAdapter(private var listOfTweet: List<Tweet>) :
    RecyclerView.Adapter<TwitterListAdapter.TwitterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwitterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tweet_list_item, parent, false)
        return TwitterViewHolder(view)
    }

    override fun onBindViewHolder(holder: TwitterViewHolder, position: Int) {
        holder.bind(listOfTweet.get(position))
    }

    override fun getItemCount(): Int {
        return listOfTweet.size
    }

    fun updateTweets(tweets: List<Tweet>) {
        listOfTweet = tweets
        notifyDataSetChanged()
    }

    class TwitterViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(tweet: Tweet) {
            view.findViewById<TextView>(R.id.date).text = tweet.date


            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                view.findViewById<TextView>(R.id.description).text =
                    Html.fromHtml(tweet.description, Html.FROM_HTML_MODE_LEGACY)
            } else {
                view.findViewById<TextView>(R.id.description).text = Html.fromHtml(tweet.description)
            }
        }


    }
}