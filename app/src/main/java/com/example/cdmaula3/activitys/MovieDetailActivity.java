package com.example.cdmaula3.activitys;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        /*int idFilme = 0;

        Intent intent = getIntent();
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Uri uri = intent.getData();
            String id = uri.getQueryParameter("id");
            idFilme = Integer.parseInt(id);
        } else {
            if (intent.getType().equals("application/json")) {
                try {
                    Log.d("intent",intent.getExtras().toString());
                    for(String k : intent.getExtras().keySet()) {
                        Log.d("intent", k);
                    }
                    JSONObject json = new JSONObject(intent.getExtras().getString("json"));
                    Log.e("intent",json.toString());
                    idFilme = json.getInt("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                idFilme = this.getIntent().getIntExtra("idFilme", 0);
            }
        }*/
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

    public void onClickShareTwitter(View v){
        PackageManager pm = getPackageManager();
        try {
            Intent twitterIntent = new Intent(Intent.ACTION_SEND);
            twitterIntent.setType("text/plain");
            String text = "Assita esse filme...";
            PackageInfo info = pm.getPackageInfo("com.twitter", PackageManager.GET_META_DATA);
        }catch (PackageManager.NameNotFoundException e){

            Toast.makeText(this, "Twitter não instalado", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickShareFilme(View v){
        String idMovie = getIntent().getExtras().getString("id");
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra("json","{\"id\":40097}");
        sendIntent.setType("application/json");

        if(sendIntent.resolveActivity(getPackageManager()) != null){
            startActivity(sendIntent);
        }else{

            Toast.makeText(getApplicationContext(),"Sem activity para abrir o json", Toast.LENGTH_SHORT).show();
        }
    }
}