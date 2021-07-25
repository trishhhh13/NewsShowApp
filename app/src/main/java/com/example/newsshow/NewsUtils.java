package com.example.newsshow;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public final class NewsUtils {
    public static final String LOG_TAG = MainActivity.class.getName();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ArrayList<News> fetchNewsData(String requestUrl) throws Exception {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s

        // Return the list of {@link Earthquake}s
        return extractNews(jsonResponse);
    }

    private static URL createUrl(String requestUrl) {
        URL url = null;
        try {
            url = new URL(requestUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }


    private NewsUtils() {
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static String makeHttpRequest(URL url) throws Exception{
        String JsonResponse = "";
        if(url == null) return null;
        final String ACCEPT_PROPERTY = "application/geo+json;version=1";
        final String USER_AGENT_PROPERTY = "newsapi.org (trishala13kohad@gmail.com)";

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", ACCEPT_PROPERTY);
            urlConnection.setRequestProperty("User-Agent",USER_AGENT_PROPERTY);
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                JsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return JsonResponse;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static ArrayList<News> extractNews(String newsJSON) {

        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }
        ArrayList<News> news = new ArrayList<>();

        try {

            JSONObject baseJSONResponse = new JSONObject(newsJSON);
            JSONArray newsArray = baseJSONResponse.getJSONArray("articles");
            for(int i = 0; i<newsArray.length();i++){
                JSONObject currentNews = newsArray.getJSONObject(i);
                String title = currentNews. getString("title");
                String date = currentNews.getString("publishedAt");
                date = date.substring(0,10);
                String newsUrl = currentNews.getString("url");
                String imageUrl = currentNews.getString("urlToImage");

                URL imgUrl = new URL(imageUrl);
                Bitmap image = BitmapFactory.decodeStream(imgUrl.openConnection().getInputStream());

                JSONObject sources = currentNews.getJSONObject("source");
                String sourceName = sources.getString("name");
                News newNews = new News(sourceName, title, newsUrl, image, date);
                news.add(newNews);
            }

        } catch (JSONException | MalformedURLException e) {
            Log.e("NewsUtils", "Problem parsing the news JSON results", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return news;
    }
}