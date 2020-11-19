package com.example.cdmaula3.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cdmaula3.R;
import com.example.cdmaula3.models.Movie;
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

        Intent it = getIntent();
        try {
            if(it.getAction().equals(Intent.ACTION_SEND)){
                String movieTitle = getIntent().getExtras().getString("title");
                tv_title = findViewById(R.id.detail_move_title);
                tv_title.setText(movieTitle);

                String overview = getIntent().getExtras().getString("overview");
                tv_description = findViewById(R.id.detail_movie_desc);
                tv_description.setText(overview);

                String imageResourceId = getIntent().getExtras().getString("imgURL");
                movieThumbnailImg = findViewById(R.id.detail_movie_img);
                Picasso.get().
                        load("https://image.tmdb.org/t/p/w342/" + imageResourceId).
                        into(movieThumbnailImg);

                String coverPhoto = getIntent().getExtras().getString("coverPhoto");
                movieCoverImg = findViewById(R.id.detail_move_cover);
                Picasso.get().
                        load("https://image.tmdb.org/t/p/w342/" + coverPhoto).
                        into(movieCoverImg);
            }
        }catch(Exception e) {
            Log.e("ERRO AQUI", e.getLocalizedMessage());
        }
    }

    void iniViews(){
        String movieTitle = getIntent().getExtras().getString("title");
        tv_title = findViewById(R.id.detail_move_title);
        tv_title.setText(movieTitle);

        String overview = getIntent().getExtras().getString("overview");
        tv_description = findViewById(R.id.detail_movie_desc);
        tv_description.setText(overview);

        String imageResourceId = getIntent().getExtras().getString("imgURL");
        movieThumbnailImg = findViewById(R.id.detail_movie_img);
        Picasso.get().
                load("https://image.tmdb.org/t/p/w342/" + imageResourceId).
                into(movieThumbnailImg);

        String coverPhoto = getIntent().getExtras().getString("coverPhoto");
        movieCoverImg = findViewById(R.id.detail_move_cover);
        Picasso.get().
                load("https://image.tmdb.org/t/p/w342/" + coverPhoto).
                into(movieCoverImg);

        //Setando a animação
        movieCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));

        getSupportActionBar().setTitle(movieTitle);
    }

    public void onClickShareFilme(View v){
        String movieTitle = getIntent().getExtras().getString("title");
        String imageResourceId = getIntent().getExtras().getString("imgURL");
        String overview = getIntent().getExtras().getString("overview");
        String coverPhoto = getIntent().getExtras().getString("coverPhoto");

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra("title", movieTitle);
        sendIntent.putExtra("imgURL", imageResourceId);
        sendIntent.putExtra("overview", overview);
        sendIntent.putExtra("coverPhoto", coverPhoto);
        sendIntent.setType("*/*");

        if(sendIntent.resolveActivity(getPackageManager()) != null){
            startActivity(sendIntent);
        }else{

            Toast.makeText(getApplicationContext(),"Sem activity para abrir o json", Toast.LENGTH_SHORT).show();
        }
    }
}