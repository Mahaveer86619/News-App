package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.newsapp.Adapter.NewsItemClicked;
import com.example.newsapp.Adapter.RecyclerViewNewsModel;
import com.example.newsapp.Adapter.RecyclerView_adapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<RecyclerViewNewsModel> newsModels = new ArrayList<>();

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        newsModels.add(new RecyclerViewNewsModel(R.drawable.ic_launcher_background, "a"));
        newsModels.add(new RecyclerViewNewsModel(R.drawable.ic_launcher_background, "b"));
        newsModels.add(new RecyclerViewNewsModel(R.drawable.ic_launcher_background, "c"));
        newsModels.add(new RecyclerViewNewsModel(R.drawable.ic_launcher_background, "d"));
        newsModels.add(new RecyclerViewNewsModel(R.drawable.ic_launcher_background, "e"));
        newsModels.add(new RecyclerViewNewsModel(R.drawable.ic_launcher_background, "f"));
        newsModels.add(new RecyclerViewNewsModel(R.drawable.ic_launcher_background, "g"));

        RecyclerView_adapter adapter = new RecyclerView_adapter(this, newsModel, new NewsItemClicked() {
            @Override
            public void onItemClicked(String item) {

            }
        });
        recyclerView.setAdapter(adapter);


    }

}