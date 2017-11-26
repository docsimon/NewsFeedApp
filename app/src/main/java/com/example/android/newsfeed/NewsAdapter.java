package com.example.android.newsfeed;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by doc on 26/11/2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Activity context, List<News> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Create the reference to the listView
        View callingListView = convertView;
        if (callingListView == null) {
            callingListView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_item, parent, false);
        }

        // reference to the News object
        News news = getItem(position);

        // reference to the newsSection textview
        TextView newsSection = (TextView) callingListView.findViewById(R.id.newsSection);
        newsSection.setText((news.getSection()));

        // reference to the newsTitle textview
        TextView newsTitle = (TextView) callingListView.findViewById(R.id.newsTitle);
        newsTitle.setText((news.getTitle()));

        // reference to the author
        TextView author = (TextView) callingListView.findViewById(R.id.author);
        author.setText(news.getAuthor());

        // reference to the date
        TextView date = (TextView) callingListView.findViewById(R.id.date);
        date.setText(news.getDate());

        return callingListView;
    }
}
