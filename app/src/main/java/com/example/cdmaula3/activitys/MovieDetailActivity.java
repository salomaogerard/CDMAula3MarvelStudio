package com.example.cdmaula3.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.cdmaula3.R;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView movieThumbnailImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        String movieTitle = getIntent().getExtras().getString("title");

        int imageResourceId = getIntent().getExtras().getInt("imgURL");
        movieThumbnailImg = findViewById(R.id.detail_movie_img);
        movieThumbnailImg.setImageResource(imageResourceId);
    }
}