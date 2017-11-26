package com.example.android.newsfeed;

import android.util.Log;

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
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by doc on 26/11/2017.
 */

public final class Utils {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = Utils.class.getSimpleName();
    private static final int READ_TIMEOUT = 10000;
    private static final int CONNECT_TIMEOUT = 15000;
    private static final int HTTP_SUCCESS = 200;
    private static final String KEY_TITLE = "webTitle";
    private static final String KEY_SECTIONNAME = "sectionName";
    private static final String KEY_AUTHORS = "authors";
    private static final String KEY_URL = "webUrl";
    private static final String KEY_DATE = "webPublicationDate";


    /**
     * Create a private constructor because no one should ever create a {@link Utils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name Utils (and an object instance of Utils is not needed).
     */

    private Utils() {

    }


    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(READ_TIMEOUT /* milliseconds */);
            urlConnection.setConnectTimeout(CONNECT_TIMEOUT /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == HTTP_SUCCESS) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the news JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    /**
     * Return a list of {@link News} objects that has been built up from
     * parsing a JSON response.
     */
    public static List<News> fetchNews(String requestUrl) {

        // Create an empty ArrayList that we can start adding news to
        List<News> news = new ArrayList<>();

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(createUrl(requestUrl));
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            String authors = "";
            JSONObject jsonFile = new JSONObject(jsonResponse);
            JSONObject newsObject = jsonFile.optJSONObject("response");

            if (newsObject == null) {
                return null;
            }

            JSONArray results = newsObject.optJSONArray("results");

            if (results != null) {
                for (int i = 0; i < results.length(); i++) {

                    /**
                     * get the section, title,  author, date and url
                     */
                    JSONObject singleNews = results.getJSONObject(i);
                    String sectionName = singleNews.getString(KEY_SECTIONNAME);
                    String title = singleNews.getString(KEY_TITLE);
                    String url = singleNews.getString(KEY_URL);
                    String date = singleNews.getString(KEY_DATE);
                    String author = "";
                    if (singleNews.has(KEY_AUTHORS)) {
                        author = singleNews.getString(KEY_AUTHORS);
                    }
                    news.add(new News(title, sectionName, author ,date , url));
                }
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("Utils", "Problem parsing the news JSON results", e);
        }

        // Return the list of News
        return news;
    }

    /*
    This methos get a row date string and formatit with date and hours
     */
    public static String[] formatDate(String date){
        String[] tmpDate = date.split("T");
        tmpDate[1] = tmpDate[1].replaceAll("Z", "");
        return tmpDate;
    }
}
