package com.example.newsshow;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    static NewsListAdapter itemsAdapter;
    ArrayList<News> news = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String NEWS_URL = "https://newsapi.org/v2/top-headlines?country=in&apiKey=85213156800d4139a3232928cfcfac98";


        ListView newsListView = (ListView) findViewById(R.id.list_News);

        doInBackground(NEWS_URL);

        itemsAdapter = new NewsListAdapter(this, R.layout.news_list, new ArrayList<>());

        newsListView.setAdapter(itemsAdapter);

        newsListView.setOnItemClickListener((parent, view, position, id) -> {

            News currentNews = itemsAdapter.getItem(position);

            Uri uri = Uri.parse(currentNews.getUrl());

            Intent newsIntent = new Intent(Intent.ACTION_VIEW, uri);

            startActivity(newsIntent);
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void doInBackground(String url){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                news = NewsUtils.fetchNewsData(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            runOnUiThread(() -> {
                itemsAdapter.clear();

                if (news != null && !news.isEmpty()) {
                    itemsAdapter.addAll(news);
                }
            });
        });
    }

}