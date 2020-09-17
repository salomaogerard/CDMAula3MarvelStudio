package com.example.cdmaula3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cdmaula3.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.TvTitle.setText(movies.get(i).getTitle());
        myViewHolder.imageMovie.setImageResource(movies.get(i).getThumbnail());

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView TvTitle;
        private ImageView imageMovie;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            TvTitle = itemView.findViewById(R.id.item_movie_title);
            imageMovie = itemView.findViewById(R.id.item_movie_img);

        }
    }
}
