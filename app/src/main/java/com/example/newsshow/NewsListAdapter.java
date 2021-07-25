package com.example.newsshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsListAdapter extends ArrayAdapter<News> {

    public NewsListAdapter(Context context,int resource, ArrayList<News> news) {

        super(context, resource, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list, parent, false);
        }

        News news = getItem(position);

        ImageView newsImage = (ImageView) listItemView.findViewById(R.id.newsImage);
        newsImage.setImageBitmap(news.getImageResource());

        TextView title = (TextView) listItemView.findViewById(R.id.newsTitle);
        title.setText(news.getTitle());

        TextView author = (TextView) listItemView.findViewById(R.id.source);
        author.setText(news.getSource());

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        date.setText(news.getDate());

        return listItemView;
    }
}
