package com.example.cdmaula3;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cdmaula3.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class FirstPageActivity extends AppCompatActivity implements MovieItemClickListener{

    private RecyclerView moviesRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);
        getSupportActionBar().hide();

        moviesRv = findViewById(R.id.Rv_movies);

        List<Movie> movieList = new ArrayList<>();

        movieList.add(new Movie("The Avengers", R.drawable.vingadoresfilme));
        movieList.add(new Movie("The Avengers Endgame", R.drawable.avengers_endgame));
        movieList.add(new Movie("The Avengers Infinity War", R.drawable.img_9230));
        movieList.add(new Movie("The Avengers Infinity War", R.drawable.img_9230));
        movieList.add(new Movie("The Avengers Infinity War", R.drawable.img_9230));
        movieList.add(new Movie("The Avengers Infinity War", R.drawable.img_9230));

        MovieAdapter movieAdapter = new MovieAdapter(this, movieList, this);
        moviesRv.setAdapter(movieAdapter);
        moviesRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {

        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgURL", movie.getThumbnail());

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(FirstPageActivity.this, movieImageView, "sharedName");

        startActivity(intent, options.toBundle());

        Toast.makeText(this, "item clicked : " + movie.getTitle(), Toast.LENGTH_LONG).show();

    }
}
