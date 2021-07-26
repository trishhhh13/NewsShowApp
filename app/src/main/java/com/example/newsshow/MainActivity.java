package com.example.newsshow;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static NewsListAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String NEWS_URL = "https://newsapi.org/v2/top-headlines?country=in&apiKey=85213156800d4139a3232928cfcfac98";
        ListView newsListView = (ListView) findViewById(R.id.list_News);

        NewsAsyncTask task = new NewsAsyncTask();
        task.execute(NEWS_URL);

        itemsAdapter = new NewsListAdapter(this, R.layout.news_list, new ArrayList<>());

        newsListView.setAdapter(itemsAdapter);

        newsListView.setOnItemClickListener((parent, view, position, id) -> {

            News currentNews = itemsAdapter.getItem(position);

            Uri uri = Uri.parse(currentNews.getUrl());

            Intent newsIntent = new Intent(Intent.ACTION_VIEW, uri);

            startActivity(newsIntent);
        });

    }

    private static class NewsAsyncTask extends AsyncTask<String, Void, ArrayList<News>> {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected ArrayList<News> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            ArrayList<News> news = new ArrayList<>();
            try {
                news = NewsUtils.fetchNewsData(urls[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return news;
        }

        @Override
        protected void onPostExecute(ArrayList<News> data) {

            itemsAdapter.clear();

            if (data != null && !data.isEmpty()) {
                itemsAdapter.addAll(data);
            }
        }
    }
}