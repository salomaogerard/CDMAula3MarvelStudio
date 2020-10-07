package com.example.cdmaula3.services.response;

import com.squareup.moshi.Json;

public class MoviesResponse {

    @Json(name = "poster_path")
    private final String posterPath;

    @Json(name = "original_title")
    private final String originalTitle;

    public MoviesResponse(String posterPath, String originalTitle) {
        this.posterPath = posterPath;
        this.originalTitle = originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }
}
