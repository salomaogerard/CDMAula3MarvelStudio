package com.example.cdmaula3.services;

import com.example.cdmaula3.models.Movie;

import java.util.List;

public interface MovieContractServic {

    interface listMoviesView {

        void showMovies(List<Movie> movies);

        void showError();

    }


    interface listMoviesPresenter {

        void getMovies();

        void destroyView();
        
    }
}
