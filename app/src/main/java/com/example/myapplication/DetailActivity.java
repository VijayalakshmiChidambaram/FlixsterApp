package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvOverview;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        ratingBar = findViewById(R.id.ratingBar);

        //Retrieve the data in the detail activity screen corresponding to the key
        //Display the title in the activity details xml files so we can display in the screen the data we are pulling out
        String title = getIntent().getStringExtra("title");
        tvTitle.setText(title);

    }
}