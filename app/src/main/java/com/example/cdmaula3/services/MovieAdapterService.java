package com.example.cdmaula3.services;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cdmaula3.R;
import com.example.cdmaula3.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapterService extends RecyclerView.Adapter<MovieAdapterService.MyViewHolder> {

    private List<Movie> movies;
    private static itemClickListenerService movieItemClickListener;

    public MovieAdapterService(itemClickListenerService movieItemClickListener) {
        MovieAdapterService.movieItemClickListener = movieItemClickListener;

        movies = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.bind(movies.get(i));
    }

    @Override
    public int getItemCount() {
        return (movies != null && movies.size() > 0) ? movies.size() : 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private ImageView imageMovie;
        private Movie movie;
        private TextView overview;

        public MyViewHolder(View itemView){
            super(itemView);

            tvTitle = itemView.findViewById(R.id.item_movie_title);
            imageMovie = itemView.findViewById(R.id.item_movie_img);


            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(movieItemClickListener != null) {
                        movieItemClickListener.onMovieClick(movie, imageMovie);
                    }
                }
            });

        }

        public void bind(Movie movie) {
            this.movie = movie;

            tvTitle.setText(movie.getTitle());
            Picasso.get()
                    .load("https://image.tmdb.org/t/p/w342/" + movie.getPathPoster())
                    .into(imageMovie);

        }

    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public interface itemClickListenerService {
        void onMovieClick(Movie movie, ImageView movieImageView);
    }
}
