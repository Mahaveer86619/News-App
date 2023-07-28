package com.sev7en.newsapp.Screens

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.sev7en.newsapp.Adapters.ContentRecyclerViewAdapter
import com.sev7en.newsapp.Adapters.DataModelContent
import com.sev7en.newsapp.Adapters.ItemClicked
import com.sev7en.newsapp.Api.MySingletonClass
import com.sev7en.newsapp.databinding.ActivityHomeScreenBinding


class HomeScreen : AppCompatActivity(), ItemClicked {

    private lateinit var binding : ActivityHomeScreenBinding
    private lateinit var madapter: ContentRecyclerViewAdapter
    val apikey = "cbf82fbfdf90ffdb363b9a4bc7a5364e"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvContent.layoutManager = LinearLayoutManager(this)
        fetchdata("https://gnews.io/api/v4/top-headlines?category=general&lang=en&country=in&max=10&apikey=$apikey")
        madapter = ContentRecyclerViewAdapter(this)
        binding.rvContent.adapter = madapter

        var search : String

        binding.etSearchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.etSearchBar.text.isEmpty()){
                    fetchdata("https://gnews.io/api/v4/top-headlines?category=general&lang=en&country=in&max=10&apikey=$apikey")
                    binding.tvAction.text = "Top Headlines"
                } else {
                    search = binding.etSearchBar.text.toString()
                    fetchdata("https://gnews.io/api/v4/search?q=$search&lang=en&country=in&max=10&apikey=$apikey")
                    binding.tvAction.text = "Searched"
                }
            }
        })

    }

    private fun fetchdata (url : String)  {

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            {
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
        val intent = CustomTabsIntent.Builder()
            .build()
        intent.launchUrl(this@HomeScreen, Uri.parse(news.url))
    }
}