package com.example.cdmaula3.services;

import android.util.Log;

import com.example.cdmaula3.services.response.MoviesResponse;
import com.example.cdmaula3.services.response.MoviesResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/popular")
    Call<MoviesResult> getPopularMovies(@Query("api_key") String keyApi,
                                        @Query("language") String languageApi);
    @GET("movie/now_playing")
    Call<MoviesResult> getNowPlaying(@Query("api_key") String keyApi,
                                     @Query("language") String languageApi);

    @GET("movie/{movieId}")
    Call<MoviesResult> getMovieWithId(@Path("idMovie") String idMovie,
                                  @Query("api_key") String keyApi,
                                  @Query("language") String languageApi);

}
