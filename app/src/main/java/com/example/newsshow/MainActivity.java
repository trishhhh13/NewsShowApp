package com.example.newsshow;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    NewsListAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String NEWS_URL = "https://newsapi.org/v2/top-headlines?country=in&apiKey=85213156800d4139a3232928cfcfac98";
        ListView earthquakeListView = (ListView) findViewById(R.id.list_News);

        // Start the AsyncTask to fetch the earthquake data
        NewsAsyncTask task = new NewsAsyncTask();
        task.execute(NEWS_URL);

        // Create a new {@link ArrayAdapter} of earthquakes
        itemsAdapter = new NewsListAdapter(this, R.layout.news_list, new ArrayList<>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(itemsAdapter);

    }

    private class NewsAsyncTask extends AsyncTask<String, Void, ArrayList<News>> {

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
            // Clear the adapter of previous earthquake data
            itemsAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                itemsAdapter.addAll(data);
            }
        }
    }
}