package com.example.cdmaula3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cdmaula3.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class FirstPageActivity extends AppCompatActivity {

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

        MovieAdapter movieAdapter = new MovieAdapter(this, movieList);
        moviesRv.setAdapter(movieAdapter);
        moviesRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

}
