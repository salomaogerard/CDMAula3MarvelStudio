package com.example.cdmaula3.services;

import android.content.Intent;

import com.example.cdmaula3.models.Movie;

public interface DetailContract {

    interface ListDetailView {

        void showDetail(Movie movie);

        void showError();
    }

    interface ListDetailPresenter {

        void getDetail(Intent intent);

    }
}
