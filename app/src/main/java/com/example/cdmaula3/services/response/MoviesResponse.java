package com.example.cdmaula3.services.response;

import com.squareup.moshi.Json;

public class MoviesResponse {

    @Json(name = "id")
    private final String idMovie;

    @Json(name = "poster_path")
    private final String posterPath;

    @Json(name = "original_title")
    private final String originalTitle;

    @Json(name = "overview")
    private final String overView;

    public MoviesResponse(String posterPath, String originalTitle, String overView, String idMovie) {
        this.posterPath = posterPath;
        this.originalTitle = originalTitle;
        this.overView = overView;
        this.idMovie = idMovie;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverView(){
        return overView;
    }

    public String getIdMovie(){
        return idMovie;
    }
}
