package com.example.cdmaula3.activitys;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cdmaula3.R;
import com.example.cdmaula3.models.Movie;
import com.example.cdmaula3.services.MovieAdapterService;
import com.example.cdmaula3.services.MovieContractServic;
import com.example.cdmaula3.services.MoviePresenterService;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class FirstPageActivity extends AppCompatActivity
        implements MovieContractServic.listMoviesView,
        MovieAdapterService.itemClickListenerService {

    private MovieAdapterService movieAdapter;
    private MovieContractServic.listMoviesPresenter presenter;
    private Button buttonSignOut;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);
        getSupportActionBar().hide();

        configurateAdapter();

        buttonSignOut = findViewById(R.id.buttonSignOut);
        firebaseAuth = FirebaseAuth.getInstance();

        presenter = new MoviePresenterService(this);
        presenter.getMovies();

        buttonSignOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent it = new Intent(FirstPageActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
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
