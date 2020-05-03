package com.reddit.kotlinservice

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.reddit.kotlinnews.R

class RedditService(context:Context) {

    var mContext:Context;

    init {
        mContext = context;
    }

    fun fetchData(){
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(mContext)
        val url = mContext.getString(R.string.server_url);

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                //textView.text = "Response is: ${response.substring(0, 500)}"
            },
            Response.ErrorListener { //textView.text = "That didn't work!"
                 })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}