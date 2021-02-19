package com.example.myapplication.Adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.models.Movies;

import java.util.List;

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
    //Viewholder is the representation of the row in UI
    public class ViewHolder extends RecyclerView.ViewHolder{
    //Get reference to each of the components in the view as we bind data appropriately in each part
        //Define member variables for each view in teh ViewHolder

        ImageView ivPoster;
        TextView tvTitle;
        TextView tvOverview;

        //Constructor - Define where the text view and image View are coming from
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
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
            
            Glide.with(context).load(imageURL).into(ivPoster);
        }
    }
}