package com.example.android.newsfeed;

/**
 * Created by doc on 26/11/2017.
 */

public class News {
    private String title;
    private String section;
    private String date;
    private String url;
    private String contributors;

    /*
    Public constructor
     */
    public News(String title, String section, String date, String url, String contributors) {
        this.title = title;
        this.section = section;
        this.date = date;
        this.url = url;
        this.contributors = contributors;
    }

    /*
    Getters
     */

    public String getTitle() {
        return this.title;
    }

    public String getSection() {
        return this.section;
    }

    public String getDate() {
        return this.date;
    }

    public String getUrl() {
        return this.url;
    }

    public String getContributors() {
        return this.contributors;
    }
}