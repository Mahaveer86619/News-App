package com.example.newsapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;

import java.util.ArrayList;

public class RecyclerView_adapter extends RecyclerView.Adapter<RecyclerView_adapter.ViewHolder> {

    Context context;
    ArrayList<RecyclerViewNewsModel> arrnews;

    public RecyclerView_adapter (Context context, ArrayList<RecyclerViewNewsModel> arrNews) {
        this.context = context;
        this.arrnews = arrNews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.news_title.setText(arrnews.get(position).title);
        holder.news_img.setImageResource(arrnews.get(position).img);
    }

    @Override
    public int getItemCount() {
        return arrnews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView news_img;
        TextView news_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            news_img = itemView.findViewById(R.id.news_img_iv);
            news_title = itemView.findViewById(R.id.news_heading_tv);
        }
    }
}
