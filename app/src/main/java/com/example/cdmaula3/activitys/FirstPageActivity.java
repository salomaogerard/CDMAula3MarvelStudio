package com.example.cdmaula3.activitys;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cdmaula3.R;
import com.example.cdmaula3.models.Movie;
import com.example.cdmaula3.services.MovieAdapterService;
import com.example.cdmaula3.services.MovieContractServic;
import com.example.cdmaula3.services.MoviePresenterService;

import java.util.List;

public class FirstPageActivity extends AppCompatActivity
        implements MovieContractServic.listMoviesView,
        MovieAdapterService.itemClickListenerService {

    private MovieAdapterService movieAdapter;
    private MovieContractServic.listMoviesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);
        getSupportActionBar().hide();

        configurateAdapter();

        presenter = new MoviePresenterService(this);
        presenter.getMovies();
    }

    private void configurateAdapter(){
        final RecyclerView recyclerMovies = findViewById(R.id.Rv_movies);

        movieAdapter = new MovieAdapterService( this);
        recyclerMovies.setAdapter(movieAdapter);
        recyclerMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void showMovies(List<Movie> movies) {
        movieAdapter.setMovies(movies);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Erro ao obter lista de filmes.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void  onDestroy(){
        super.onDestroy();
        presenter.destroyView();
    }

    @Override
    public void onMovieClick(Movie movie) {

//        Intent intent = new Intent(this, MovieDetailActivity.class);
//        intent.putExtra("title", movie.getTitle());
//        intent.putExtra("imgURL", movie.getPathPoster());
//
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(FirstPageActivity.this);
//
//        startActivity(intent, options.toBundle());
//
//        Toast.makeText(this, "item clicked : " + movie.getTitle(), Toast.LENGTH_LONG).show();

    }
}
