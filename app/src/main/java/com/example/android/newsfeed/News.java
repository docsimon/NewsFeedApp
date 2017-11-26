package com.example.android.newsfeed;

/**
 * Created by doc on 26/11/2017.
 */

public class News {
    private String title;
    private String section;
    private String author;
    private String date;

    /*
    Public constructor
     */
    public News(String title, String section, String author, String date){
        this.title = title;
        this.section = section;
        this.author = author;
        this.date = date;

    }

    /*
    Getters
     */

    public String getTitle(){
        return this.title;
    }
    public String getSection(){
        return this.section;
    }
    public String getAuthor(){
        return this.author;
    }
    public String getDate(){
        return this.date;
    }
}