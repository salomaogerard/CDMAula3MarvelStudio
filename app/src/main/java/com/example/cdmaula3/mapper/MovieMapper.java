package com.example.cdmaula3.mapper;

import com.example.cdmaula3.models.Movie;
import com.example.cdmaula3.services.response.MoviesResponse;

import java.util.ArrayList;
import java.util.List;

public class MovieMapper {

    public static List<Movie> toResponseAtDomine(List<MoviesResponse> moviesResponseList){
        List<Movie> movieList = new ArrayList<>();

        for(MoviesResponse moviesResponse : moviesResponseList){
            final Movie movie = new Movie(moviesResponse.getOriginalTitle(), moviesResponse.getPosterPath(),moviesResponse.getOverView(), moviesResponse.getIdMovie(), moviesResponse.getCoverPhoto());
            movieList.add(movie);
        }

        return movieList;
    }

    public static Movie toResponseAtDomineOne(MoviesResponse movieResponse) {

        final Movie movie = new Movie(movieResponse.getOriginalTitle(),
                movieResponse.getPosterPath(), movieResponse.getOverView(),
                movieResponse.getCoverPhoto(), movieResponse.getIdMovie());

        return movie;
    }

}
