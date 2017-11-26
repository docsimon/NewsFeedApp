package com.example.android.newsfeed;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<News>> {

    private static final int NEWS_LOADER_ID = 1;
    NewsAdapter newsAdapter;
    TextView emptyView;
    ProgressBar progressBar;
    private static final String NEWS_REQUEST_URL = "https://content.guardianapis.com/search?api-key=***REMOVED***e417";
    private String queryString = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queryString = NEWS_REQUEST_URL;
        // Check network connection
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();


        // create an instance of empty view
        emptyView = (TextView) findViewById(R.id.empty_view);
        // Instance of Listview
        final ListView newsListView = (ListView) findViewById(R.id.list);
        newsListView.setEmptyView(emptyView);

        if (!isConnected) {
            String net_problem = getString(R.string.net_status);
            emptyView.setText(net_problem);
            progressBar = (ProgressBar) findViewById(R.id.loading_spinner);
            progressBar.setVisibility(View.GONE);

        }{

            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);

            // Instance of NewsAdapter
            // Create a new {@link ArrayAdapter} of news
            newsAdapter = new NewsAdapter(this, new ArrayList<News>());

//            List<News> news = new ArrayList<>();
//            news.add(new News("F1: Valtteri Bottas wins the season-ending Abu Dhabi GP â€“ live!", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));
//            news.add(new News("F1: Valtteri Bottas wins the season-ending Abu Dhabi GP â€“ live!", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));
//            news.add(new News("F1: Valtteri Bottas wins the season-ending Abu Dhabi GP â€“ live!", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));
//            news.add(new News("F1: Valtteri Bottas wins the season-ending Abu Dhabi GP â€“ live!", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));
//            news.add(new News("We need new words to explain these curious times. How about â€˜coffusedâ€™ or â€˜procrastinetflixâ€™? | Arwa Mahdawi", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));
//            news.add(new News("We need new words to explain these curious times. How about â€˜coffusedâ€™ or â€˜procrastinetflixâ€™? | Arwa Mahdawi", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));
//            news.add(new News("We need new words to explain these curious times. How about â€˜coffusedâ€™ or â€˜procrastinetflixâ€™? | Arwa Mahdawi", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));
//            news.add(new News("We need new words to explain these curious times. How about â€˜coffusedâ€™ or â€˜procrastinetflixâ€™? | Arwa Mahdawi", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));
//            news.add(new News("We need new words to explain these curious times. How about â€˜coffusedâ€™ or â€˜procrastinetflixâ€™? | Arwa Mahdawi", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));
//            news.add(new News("We need new words to explain these curious times. How about â€˜coffusedâ€™ or â€˜procrastinetflixâ€™? | Arwa Mahdawi", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));
//            news.add(new News("Footbal", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));
//            news.add(new News("Footbal", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));
//            news.add(new News("Footbal", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));
//            news.add(new News("Footbal", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));
//            news.add(new News("Footbal", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));
//            news.add(new News("Footbal", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));
//            news.add(new News("Footbal", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));
//            news.add(new News("Footbal", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));
//            news.add(new News("Footbal", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));
//            news.add(new News("Footbal", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));
//            news.add(new News("Footbal", "Sport", "Ciccio Formaggio", "2017-11-26T15:07:26Z"));


            newsAdapter = new NewsAdapter(this, new ArrayList<News>());

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            newsListView.setAdapter(newsAdapter);

            newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    News listItem = (News) newsListView.getItemAtPosition(position);
                    String url = listItem.getUrl();
                    // Convert the String URL into a URI object (to pass into the Intent constructor)
                    Uri newsUri = Uri.parse(url);
                    // Create a new intent to view the news URI
                    Intent newsUrlPage = new Intent(Intent.ACTION_VIEW, newsUri);
                    startActivity(newsUrlPage);
                }
            });
        }
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new NewsLoader(this, queryString);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        // Clear the adapter of previous data
        newsAdapter.clear();
        String empty_list = getString(R.string.empty_list);
        emptyView.setText(empty_list);
        progressBar = (ProgressBar) findViewById(R.id.loading_spinner);
        progressBar.setVisibility(View.GONE);

        // If there is a valid list of {@link News}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (news != null && !news.isEmpty()) {
            newsAdapter.addAll(news);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        // Loader reset, so we can clear out our existing data.
        newsAdapter.clear();
    }
}