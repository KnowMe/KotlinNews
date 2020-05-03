package com.reddit.kotlinnews

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import org.json.JSONObject


class DetailActivity : AppCompatActivity() {
    lateinit var jsonObject:JSONObject
    val picasso: Picasso = Picasso.get()
    lateinit var imageView: ImageView
    lateinit var textviewDetail:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        setup()
    }

    fun setup()
    {
        var detailString:String = intent.getStringExtra("item")
        jsonObject = JSONObject(detailString)

        setTitle(jsonObject.getJSONObject("data").getString("title"))


        imageView = findViewById(R.id.imgageview)
        textviewDetail = findViewById(R.id.textviewDetail)

        textviewDetail.setText(jsonObject.getJSONObject("data").getString("selftext"))

        if(textviewDetail.text == null || textviewDetail.text.length ==0)
        {
            textviewDetail.setText("No data found.")
        }
        else
        {
            textviewDetail.setMovementMethod(ScrollingMovementMethod())
        }

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

    override fun onSupportNavigateUp() :Boolean{
        onBackPressed()
        return true
    }

}
