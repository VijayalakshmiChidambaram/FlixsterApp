package com.example.myapplication.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Movie;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.DetailActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.models.Movies;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleWithBorderTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

//Base RV adapter is an abstract class, so implement methods
//Display list of movies in the screen - RV
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    //Key details to fill in the methods
    //Context - where the adapter is being constructed from : Find that
    Context context;
    //Actual data - List of movie that adapter needs to hold on to
    List<Movies> movies;

    public MovieAdapter(Context context, List<Movies> movies) {
        this.context = context;
        this.movies = movies;
    }

    //Usually involves inflating the layout(item_movie) and return it inside ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //To know what happens behind RV add log statements
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);

    }

    //Populating the data into the View through ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //To know what happens behind RV add log statements
        Log.d("MovieAdapter", "onBindViewHolder" + position);
        //Get the movie at the passed in position
        Movies movie = movies.get(position);
        //Bind the movie data into ViewHolder
        holder.bind(movie);
    }

    //Returns the total count of items in the list(List of movies).
    //Size of the list is the number of items available
    @Override
    public int getItemCount() {
        return movies.size();
    }

    //To implement Adapter, first define ViewHolder class
    //Viewholder is the representation of the row in UI. Hold the references to each row of the element in the RV
    public class ViewHolder extends RecyclerView.ViewHolder {
        //Get reference to each of the components in the view as we bind data appropriately in each part
        //Define member variables for each view in teh ViewHolder

        RelativeLayout container;
        ImageView ivPoster;
        TextView tvTitle;
        TextView tvOverview;
        ImageView playIcon;

        //Constructor - Define where the text view and image View are coming from
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            container = itemView.findViewById(R.id.container);
            playIcon = itemView.findViewById(R.id.playIcon);
        }

        //Get getter method to populate each of these views
        public void bind(Movies movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            //To render images there isn't any in built way to do, so we can use libraries
            String imageURL;
            //if phone is in landscape
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                //then imageURL = Backdrop image
                imageURL = movie.getBackdropPath();
            } else {
                //else imageURL = poster image
                imageURL = movie.getPosterpath();
            }
            int radius = 30; // corner radius, higher value = more rounded
            int margin = 20; // crop margin, set to 0 for corners with no crop
           // if (movie.getVote_rating()<5.0) {
                Glide.with(context)
                        .load(imageURL)
                        .placeholder(R.drawable.image_load)
                        .transform(new CropCircleWithBorderTransformation(radius, margin))
                        .into(ivPoster);
            /*}
            else
            {
                MovieAdapter.this.getItemId()
                initializeYouTube(youtubeKey);
            }*/
            //1) Register a click listener on whole row (On click will take to next screen)
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //2) Navigate to a new activity on tap - Using Intent
                    Intent i = new Intent(context, DetailActivity.class);
                    //Display items inside new activity screen
                    //Pass whatever data we want to display in the Details activity Screen
                    //Instead of sending each element ie.Title, Overview etc we can use Parcelable and send all data from movies.java into Details Activity(One activity to another)
                    i.putExtra("movie", Parcels.wrap(movie));
                   ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, ivPoster, "profile");
                    context.startActivity(i, options.toBundle());
                   //context.startActivity(i);
                }
            });

            ivPoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //2) Navigate to a new activity on tap - Using Intent
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(intent);
                }
            });

            playIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //2) Navigate to a new activity on tap - Using Intent
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(intent);
                }
            });

        }
    }
    }