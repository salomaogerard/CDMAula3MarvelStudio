package com.example.cdmaula3.services;

import android.util.Log;

import com.example.cdmaula3.mapper.MovieMapper;
import com.example.cdmaula3.models.Movie;
import com.example.cdmaula3.services.response.MoviesResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenterService  implements MovieContractServic.listMoviesPresenter{

    private MovieContractServic.listMoviesView view;

    public MoviePresenterService(MovieContractServic.listMoviesView view) {
        this.view = view;
    }

    @Override
    public void getMovies() {
        ApiService.getInstance()
                .getPopularMovies("925a69a753e473247f378bba545563e8")
                .enqueue(new Callback<MoviesResult>() {
                    @Override
                    public void onResponse(Call<MoviesResult> call, Response<MoviesResult> response) {
                        if (response.isSuccessful()) {
                            final List<Movie> movieList = MovieMapper
                                    .toResponseAtDomine(response.body().getMoviesResults());

                            view.showMovies(movieList);
                        } else {
                            view.showError();
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResult> call, Throwable t) {
                        Log.e("show error", t.getMessage());
                        view.showError();
                    }
                });
    }

    @Override
    public void destroyView() {
        this.view = null;
    }

}
