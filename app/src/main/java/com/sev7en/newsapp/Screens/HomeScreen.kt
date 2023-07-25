package com.sev7en.newsapp.Screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.sev7en.newsapp.Adapters.ContentRecyclerViewAdapter
import com.sev7en.newsapp.Adapters.DataModelContent
import com.sev7en.newsapp.Adapters.ItemClicked
import com.sev7en.newsapp.Api.MySingletonClass
import com.sev7en.newsapp.databinding.ActivityHomeScreenBinding

class HomeScreen : AppCompatActivity(), ItemClicked {

    private lateinit var binding : ActivityHomeScreenBinding
    private lateinit var madapter: ContentRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvContent.layoutManager = LinearLayoutManager(this)
        fetchdata()
        madapter = ContentRecyclerViewAdapter(this)
        binding.rvContent.adapter = madapter

    }
    private fun fetchdata ()  {
        val apikey = "cbf82fbfdf90ffdb363b9a4bc7a5364e"
        val url = "https://gnews.io/api/v4/top-headlines?category=general&lang=en&country=in&max=10&apikey=$apikey"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener {
                val newsJsonArray = it.getJSONArray("articles")
                val newsList = ArrayList<DataModelContent>()
                for (i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = DataModelContent(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("description"),
                        newsJsonObject.getString("image"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("publishedAt")
                    )
                    newsList.add(news)
                }
                madapter.updateList(newsList)
            }
        ) { error ->
            Log.d("Error", "news not parced : $error")
        }

// Access the RequestQueue through your singleton class.
        MySingletonClass.getInstance(this).addRequestToQueue(jsonObjectRequest)
    }

    override fun onItemClicked(news : DataModelContent) {
        Toast.makeText(this, "item clicked ${news.title}", Toast.LENGTH_SHORT).show()
    }
}