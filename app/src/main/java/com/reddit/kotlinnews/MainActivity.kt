package com.reddit.kotlinnews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.reddit.kotlindata.DataAdapter
import com.reddit.kotlinview.setDivider
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var textView:TextView
    lateinit var recyclerView: RecyclerView
    lateinit var dataList: JSONArray
    lateinit var dataAdapter:DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
        fetchData()
    }

    fun setup()
    {
        textView = findViewById(R.id.tv_message)

        textView.setOnClickListener(View.OnClickListener { view: View? ->
            textView.text = "Loading Data!"
            fetchData()
        })

        recyclerView = findViewById(R.id.recyclerView);

        dataList = JSONArray();
        dataAdapter = DataAdapter(dataList) { itemDto: JSONObject, position: Int ->
            var intent = Intent(this,DetailActivity::class.java)
            intent.putExtra("item",itemDto.toString())
            startActivity(intent)
        }
        recyclerView.setAdapter(dataAdapter);
        recyclerView.setLayoutManager(LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setDivider(R.drawable.recycler_view_divider)
    }
    fun fetchData(){
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = this.getString(R.string.server_url);

        var jsonObject = JSONObject();
        // Request a string response from the provided URL.
        val stringRequest = com.android.volley.toolbox.JsonObjectRequest(
            Request.Method.GET, url,
            jsonObject,
            Response.Listener<JSONObject> { response ->
                var noDataFound = false
                if(response == null || !response.has("data"))
                    noDataFound = true
                else
                {
                    dataList =
                        (response.get("data") as JSONObject).getJSONArray("children")
                    if (dataList.length() == 0)
                        noDataFound = true
                    else {
                        dataAdapter.setData(dataList)
                        dataAdapter.notifyDataSetChanged()
                        textView.visibility = View.INVISIBLE
                    }
                }
                if(noDataFound)
                {
                    textView.text = "No Data found.\n" +
                            "click me to retry!"
                }
            },
            Response.ErrorListener {
                textView.text = "Please check your network connection.\nclick me to retry!"
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}
