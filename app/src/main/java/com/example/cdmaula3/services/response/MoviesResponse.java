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

    @Json(name = "backdrop_path")
    private final String coverPhoto;

    public MoviesResponse(String posterPath, String originalTitle, String overView, String idMovie, String coverPhoto) {
        this.coverPhoto = coverPhoto;
        this.idMovie = idMovie;
        this.posterPath = posterPath;
        this.originalTitle = originalTitle;
        this.overView = overView;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public String getIdMovie() {
        return idMovie;
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
