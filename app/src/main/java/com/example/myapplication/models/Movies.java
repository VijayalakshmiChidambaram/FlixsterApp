package com.example.myapplication.models;

import android.graphics.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Parse results into actual movies(Movie objects)
public class Movies {
    String posterpath;
    String title;
    String overview;
    String backdropPath;

    //Constructor to take JSON object to construct a movie object
    public Movies(JSONObject jsonObject) throws JSONException
    //Take in JSON Object and read the fields required
    {
        //For the landscape mode parse the backdroppath
        backdropPath = jsonObject.getString("backdrop_path");
        posterpath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
    }
    //Method to return list of movies from JSONArray
    //Takes in from JSONArray (Actual data we got back from DB)
    public static List<Movies> fromJSONArray(JSONArray moviesJsonArray) throws JSONException {
        //Constructing movie for each element in JSONArray
        List<Movies> movies = new ArrayList<>();
        //Add a movie to each position of the array and we are just calling the constructor
        for (int i =0; i<moviesJsonArray.length();i++)
        {
            movies.add(new Movies(moviesJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    //To get data out of movie objects declared above

    public String getPosterpath() {
        // Make posterpath usable
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterpath);
    }

    //Getter method for the backdropPath
    public String getBackdropPath(){
        return String.format("https://image.tmdb.org/t/p/w342/%s",backdropPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
