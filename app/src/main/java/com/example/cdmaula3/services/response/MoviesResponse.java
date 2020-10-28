package com.example.cdmaula3.services.response;

import com.squareup.moshi.Json;

public class MoviesResponse {

    @Json(name = "poster_path")
    private final String posterPath;

    @Json(name = "original_title")
    private final String originalTitle;

    @Json(name = "overview")
    private final String overView;

    public MoviesResponse(String posterPath, String originalTitle, String overView) {
        this.posterPath = posterPath;
        this.originalTitle = originalTitle;
        this.overView = overView;
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
}
