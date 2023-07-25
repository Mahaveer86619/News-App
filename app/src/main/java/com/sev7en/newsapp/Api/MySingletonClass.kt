package com.sev7en.newsapp.Api

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class MySingletonClass(context: Context) {
    companion object{
        @Volatile
        private var INSTANCE : MySingletonClass? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MySingletonClass(context).also {
                    INSTANCE = it
                }
            }
    }
    private val requestQueue : RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }
    fun <T> addRequestToQueue (req : Request<T>) {
        requestQueue.add(req)
    }
}