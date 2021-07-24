package com.example.newsshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsListAdapter extends ArrayAdapter<News> {

    public NewsListAdapter(Context context, ArrayList<News> news) {

        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list, parent, false);
        }

        News news = getItem(position);

//        ImageView newsImage = (ImageView) listItemView.findViewById(R.id.imageView);
//        newsImage.setImageResource(news.getImageResource());

        TextView title = (TextView) listItemView.findViewById(R.id.newsTitle);
        title.setText(news.getTitle());

        TextView author = (TextView) listItemView.findViewById(R.id.author);
        author.setText(news.getAuthor());

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        date.setText(news.getDate());

        return listItemView;
    }
}
