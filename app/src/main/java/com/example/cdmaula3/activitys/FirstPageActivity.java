package com.example.cdmaula3.activitys;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

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

    public static final String INFO_TYPE = "INFO_TYPE";
    private MovieAdapterService movieAdapter, movieAdapter2;
    private MovieContractServic.listMoviesPresenter presenter;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);

        configurateAdapter();

        firebaseAuth = FirebaseAuth.getInstance();

        presenter = new MoviePresenterService(this);
        presenter.getMovies();

        presenter = new MoviePresenterService(this);
        presenter.getPopularMovies();

//        this.getType();
    }

    private void getType() {
        if(getIntent().getStringExtra(INFO_TYPE).equals("favorite")) {
            presenter = new MoviePresenterService(this);
            presenter.getMovies();
        }
        else if(getIntent().getStringExtra(INFO_TYPE).equals("popular")){
            presenter = new MoviePresenterService(this);
            presenter.getPopularMovies();
        }
    }

    private void configurateAdapter(){
        final RecyclerView recyclerMovies = findViewById(R.id.Rv_popularMovies);

        movieAdapter = new MovieAdapterService( this);
        recyclerMovies.setAdapter(movieAdapter);
        recyclerMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        final RecyclerView recyclerMovies2 = findViewById(R.id.Rv_popularWeekMovies);
        movieAdapter2 = new MovieAdapterService(this);
        recyclerMovies2.setAdapter(movieAdapter2);
        recyclerMovies2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void showMovies(List<Movie> movies) {
        movieAdapter.setMovies(movies);
        movieAdapter2.setMovies(movies);
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {

        Intent intent = new Intent(this, MovieDetailActivity.class);

        intent.putExtra("objeto", movie);
        intent.setAction(Intent.ACTION_SEND);

        startActivity(intent);
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_logout){
            logoutUser();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logoutUser(){
        firebaseAuth.signOut();
        Intent it = new Intent(FirstPageActivity.this, LoginActivity.class);
        startActivity(it);
        Toast.makeText(this, "Deslogado com sucesso!", Toast.LENGTH_SHORT).show();
    }


}
