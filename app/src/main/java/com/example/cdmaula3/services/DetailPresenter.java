package com.example.cdmaula3.services;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.example.cdmaula3.models.Movie;

public class DetailPresenter implements DetailContract.ListDetailPresenter, MovieContractServic.ListOneMovie{

    private DetailContract.ListDetailView view;
    private Movie movie;

    public DetailPresenter (DetailContract.ListDetailView view) {
        this.view = view;
    }

    @Override
    public void getDetail(Intent intent) {

        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Uri uri = intent.getData();
            String id = uri.getQueryParameter("idMovie");

            Log.e("erro aqui", intent.getAction() + " " + intent.getData());
            if(!id.isEmpty()) {
                MoviePresenterService get = new MoviePresenterService(this);
                get.getMovieWithId(id);
            }
            else {
                view.showError();
            }
        }
        else {
//            movie = (Movie) intent.getSerializableExtra(infoMovie);
            view.showDetail(movie);
        }

    }

    @Override
    public void showInfo(Movie movie) {
        view.showDetail(movie);
    }

    @Override
    public void showError() {
        view.showError();
    }
}
