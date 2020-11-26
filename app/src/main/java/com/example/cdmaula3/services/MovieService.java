package com.example.cdmaula3.services;

import com.example.cdmaula3.services.response.MoviesResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/popular")
    Call<MoviesResult> getPopularMovies(@Query("api_key") String keyApi);

}
