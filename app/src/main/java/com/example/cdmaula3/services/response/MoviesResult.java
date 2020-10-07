package com.example.cdmaula3.services.response;

import com.squareup.moshi.Json;

import java.util.List;

public class MoviesResult {

    @Json(name = "results")
    private final List<MoviesResponse> moviesResults;

    public MoviesResult(List<MoviesResponse> moviesResults) {
        this.moviesResults = moviesResults;
    }

    public List<MoviesResponse> getMoviesResults() {
        return moviesResults;
    }
}
