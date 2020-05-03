package com.reddit.kotlinview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reddit.kotlinnews.R
import com.squareup.picasso.Picasso
import org.json.JSONObject

class RedditViewHolder(itemView: View, picaso: Picasso) : RecyclerView.ViewHolder(itemView) {

    lateinit var imageView:ImageView
    lateinit var textViewtitle:TextView
    lateinit var picasso:Picasso
    lateinit var view: View

    init {
        imageView = itemView.findViewById(R.id.imgageview)
        textViewtitle = itemView.findViewById(R.id.textviewtitle)
        picasso = picaso
        view =itemView
    }

    fun populateData(jsonObject: JSONObject)
    {
        textViewtitle.setText(jsonObject.getJSONObject("data").getString("title"))

        var thumbnail:String = jsonObject.getJSONObject("data").getString("thumbnail")

        if(thumbnail == "")
        {
            imageView.visibility = View.GONE;
        }
        else
        {
            imageView.visibility = View.VISIBLE;
            picasso.load(thumbnail)
                .into(imageView)
        }

    }

}