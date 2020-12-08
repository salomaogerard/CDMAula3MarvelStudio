package com.example.cdmaula3.services;

import android.util.Log;

import com.example.cdmaula3.mapper.MovieMapper;
import com.example.cdmaula3.models.Movie;
import com.example.cdmaula3.services.response.MoviesResponse;
import com.example.cdmaula3.services.response.MoviesResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenterService  implements MovieContractServic.listMoviesPresenter{

    private MovieContractServic.listMoviesView view;
    private Movie movie;
    private MovieContractServic.ListOneMovie viewOne;

    public MoviePresenterService(MovieContractServic.listMoviesView view) {
        this.view = view;
    }

    public MoviePresenterService(MovieContractServic.ListOneMovie viewOne) {
        this.viewOne = viewOne;
    };

    @Override
    public void getMovies() {
        ApiService.getInstance()
                .getNowPlaying("925a69a753e473247f378bba545563e8", "pt-BR")
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
    public void getPopularMovies() {
        ApiService.getInstance()
                .getPopularMovies("925a69a753e473247f378bba545563e8", "pt-BR")
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
                        view.showError();
                    }
                });
    }

    @Override
    public void getMovieWithId(String id) {

        ApiService.getInstance()
                .getMovieWithId(id, "925a69a753e473247f378bba545563e8", "pt-BR")
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
                        view.showError();
                    }
                });
    }

    @Override
    public void destroyView() {
        this.view = null;
    }

}
