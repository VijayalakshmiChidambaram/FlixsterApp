package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.myapplication.Adapters.MovieAdapter;
import com.example.myapplication.models.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
        //Making a network request
        //Get request on the URL to get the currently playing movies
    public static final String Movie_Playing_URL ="https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        // TAG - To log success or failure data using Log statement
    public static final String TAG = "MainActivity";
    List<Movies> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        movies = new ArrayList<>();

        //Bind the adapter to the Data Source to populate RV
        //Create an adapter
        MovieAdapter movieAdapter = new MovieAdapter(this, movies);

        //Set the adapter on RV
        rvMovies.setAdapter(movieAdapter);
        //Set a Layout Manager - Required by RV to layout different views on to the screen
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        //Instance of AsyncHttpClient creation
        AsyncHttpClient client = new AsyncHttpClient();
        //Get request on the URL and pass callback here - JSONResponse handler as the Movie DB uses JSON to return request
        client.get(Movie_Playing_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                //Data we need is inside json object, so we can pull that data now
                JSONObject jsonObject = json.jsonObject;
                //Inside jsonObject get a JSON array called results
                // When examine and parse data on JSON, the key may not exists or they might be some issue so try-catch
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    //If JSON request success log that at info level 'i'
                    Log.i(TAG, "Results :" + results.toString());
                    //Call movies.java from MainActivity
                    movies.addAll(Movies.fromJSONArray(results));
                    //Whenever data changes behind the adapter we need to let the adapter know so it re renders the RV
                    movieAdapter.notifyDataSetChanged();
                    //To check if the data we get into movies is good - Log
                    Log.i(TAG, "Movies" + movies.size());
                } catch (JSONException e) {
                    Log.e(TAG, "JSON EXCEPTION THROWN", e);
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });

    }
}
