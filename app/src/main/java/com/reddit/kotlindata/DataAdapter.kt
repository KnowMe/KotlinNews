package com.reddit.kotlindata

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reddit.kotlinnews.R
import com.reddit.kotlinview.RedditViewHolder
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONObject

class DataAdapter(datalst: JSONArray, val clickListener:(JSONObject, Int) -> Unit) : RecyclerView.Adapter<RedditViewHolder>() {

    lateinit var dataList:JSONArray
    val picasso:Picasso = Picasso.get()

    init {
        dataList = datalst;
    }

    fun setData(datalst: JSONArray)
    {
        dataList = datalst;
        notifyDataSetChanged();
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditViewHolder {

        var viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.rowlayout,parent,false)
        return RedditViewHolder(viewHolder,picasso)
    }

    override fun onBindViewHolder(holder: RedditViewHolder, position: Int) {

        var dataItem:JSONObject = dataList.get(position) as JSONObject

        if(position == 0)
        {
            dataItem.getJSONObject("data").put("thumbnail","http://www.pilofficial.com/images/chimps2.jpg")
        }

        holder.populateData(dataItem);
        holder?.view?.setOnClickListener { clickListener(dataItem,position) }
    }

    override fun getItemCount(): Int {
        System.out.print(" getItemCount()" + dataList.length());
        return dataList.length()

    }
}