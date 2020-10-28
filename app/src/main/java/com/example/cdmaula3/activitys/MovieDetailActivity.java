package com.example.cdmaula3.activitys;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cdmaula3.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView movieThumbnailImg, movieCoverImg;
    private TextView tv_title, tv_description;
    private FloatingActionButton play_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        iniViews();
    }

    void iniViews(){
        String movieTitle = getIntent().getExtras().getString("title");
        String imageResourceId = getIntent().getExtras().getString("imgURL");
        String overview = getIntent().getExtras().getString("overview");
        getSupportActionBar().setTitle(movieTitle);

        play_fab = findViewById(R.id.play_fab);
        movieThumbnailImg = findViewById(R.id.detail_movie_img);
        Picasso.get().
                load("https://image.tmdb.org/t/p/w342/" + imageResourceId).
                into(movieThumbnailImg);

        movieCoverImg = findViewById(R.id.detail_move_cover);
        movieCoverImg.setImageResource(R.drawable.apres_la_pluie);

        tv_title = findViewById(R.id.detail_move_title);
        tv_title.setText(movieTitle);
        tv_description = findViewById(R.id.detail_movie_desc);
        tv_description.setText(overview);

        //Setando a animação
        movieCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
    }
}