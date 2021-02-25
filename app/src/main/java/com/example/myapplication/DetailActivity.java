package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myapplication.models.Movies;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.parceler.Parcels;

public class DetailActivity extends YouTubeBaseActivity {

    public static final String YOUTUBE_API_KEY = "AIzaSyAEB-Ss-fu3_bqs0N7EA7ulXnP3oRZ4ewQ";
    TextView tvTitle;
    TextView tvOverview;
    RatingBar ratingBar;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        ratingBar = findViewById(R.id.ratingBar);
        youTubePlayerView = findViewById(R.id.player);

        //Retrieve the data in the detail activity screen corresponding to the key
        //Display the title in the activity details xml files so we can display in the screen the data we are pulling out
        String title = getIntent().getStringExtra("title");
        //Unwrap the objects passed in
        Movies movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        //Convert Double to float, because Double has longer precision than float
        ratingBar.setRating((float) movie.getVote_rating());

        //Display Video - We send request to YouTube API to play YouTube videos in android app
        // Add YouTube jar file - Creating reference to YouTube library in this Android studio project
        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("DetailActivity", "onInitializationSuccess");
                youTubePlayer.cueVideo("5xVh-7ywKpE");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("DetailActivity", "onInitializationFailure");
            }
        });

    }
}

