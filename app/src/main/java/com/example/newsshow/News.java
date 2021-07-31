package com.example.newsshow;

import android.graphics.Bitmap;

public class News {

    private static String url;
    private final String source;
    private final String title;
    private final String date;
    private final Bitmap image;

    public News(String author, String title, String url, Bitmap imageUrl, String date) {
        this.source = author;
        this.title = title;
        News.url = url;
        this.image = imageUrl;
        this.date = date;
    }
    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }


    public String getDate() {
        return date;
    }

    public Bitmap getImageResource() {
        return  image;
    }
}