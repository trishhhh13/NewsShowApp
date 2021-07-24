package com.example.newsshow;

public class News {

    private String author, title, url, imageUrl, date;

    public News(String author, String title, String url, String imageUrl, String date) {
        this.author = author;
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
        this.date = date;
    }
    int imageResource = 0;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDate() {
        return date;
    }

    public int getImageResource() {
        return  imageResource;
    }
}