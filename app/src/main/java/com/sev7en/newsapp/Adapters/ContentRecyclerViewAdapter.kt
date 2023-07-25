package com.sev7en.newsapp.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sev7en.newsapp.R
import kotlin.collections.ArrayList

class ContentRecyclerViewAdapter(private val listner : ItemClicked) : RecyclerView.Adapter<ContentRecyclerViewAdapter.ViewHolder>() {

    private val itemsList : ArrayList<DataModelContent> = ArrayList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.iv_item_image)
        val title = itemView.findViewById<TextView>(R.id.tv_item_title)
        val description = itemView.findViewById<TextView>(R.id.tv_item_description)
        val date = itemView.findViewById<TextView>(R.id.tv_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_recyclerview_contents, parent, false)
        val viewHolder = ViewHolder(view)
        view.setOnClickListener{
            listner.onItemClicked(itemsList[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemsList[position]

        holder.title.text = currentItem.title
        holder.description.text = currentItem.description
        holder.date.text = currentItem.date.substring(0,10)

        Glide.with(holder.itemView.context)
            .load(currentItem.image)
            .into(holder.image)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList (updatedList : ArrayList<DataModelContent>) {
        itemsList.clear()
        itemsList.addAll(updatedList)

        // this calls the three overriden fun again for us to update the list
        notifyDataSetChanged()
    }
}

interface ItemClicked {
    fun onItemClicked(news : DataModelContent)
}