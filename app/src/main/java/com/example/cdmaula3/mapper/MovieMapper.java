package com.example.cdmaula3.mapper;

import com.example.cdmaula3.models.Movie;
import com.example.cdmaula3.services.response.MoviesResponse;

import java.util.ArrayList;
import java.util.List;

public class MovieMapper {

    public static List<Movie> toResponseAtDomine(List<MoviesResponse> moviesResponseList){
        List<Movie> movieList = new ArrayList<>();

        for(MoviesResponse moviesResponse : moviesResponseList){
            final Movie movie = new Movie(moviesResponse.getOriginalTitle(), moviesResponse.getPosterPath());
            movieList.add(movie);
        }

        return movieList;
    }
}
