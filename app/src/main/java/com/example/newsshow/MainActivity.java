package com.example.newsshow;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<News> news = new ArrayList<>();
        news.add(new News("Dummy Author1","Harry Potter and the Philosopher's stone","","","12.04.2002"));
        news.add(new News("Dummy Author2","Harry Potter and the Chamber of Secrets","","","25.05.2003"));
        news.add(new News("Dummy Author3","Harry Potter and the Prisoner of Askaban","","","04.06.2004"));
        news.add(new News("Dummy Author4","Harry Potter and the Goblet of Fire","","","18.11.2005"));
        news.add(new News("Dummy Author5","Harry Potter and the Order of Phoenix","","","13.07.2007"));
        news.add(new News("Dummy Author6","Harry Potter and the Half Blood Prince","","","06.07.2009"));
        news.add(new News("Dummy Author7","Harry Potter and the Deathly Hollows Part 1","","","19.11.2010"));
        news.add(new News("Dummy Author8","Harry Potter and the Deathly Hollows Part 2","","","07.07.2011"));

//

        NewsListAdapter itemsAdapter = new NewsListAdapter(this, news);
        ListView listView = (ListView) findViewById(R.id.list_News);
        listView.setAdapter(itemsAdapter);
    }
}