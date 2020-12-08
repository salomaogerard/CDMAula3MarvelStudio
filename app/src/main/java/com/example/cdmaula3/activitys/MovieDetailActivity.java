package com.example.cdmaula3.activitys;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cdmaula3.R;
import com.example.cdmaula3.models.Movie;
import com.example.cdmaula3.services.DetailContract;
import com.example.cdmaula3.services.DetailPresenter;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class MovieDetailActivity extends AppCompatActivity implements DetailContract.ListDetailView {

    public static final String INFO_MOVIE = "INFO_MOVIE";
    private ImageView movieThumbnailImg, movieCoverImg;
    private TextView tv_title, tv_description;
    private Movie movie;
    private DetailContract.ListDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        Intent it = getIntent();

        try {
            if(it.getAction().equals(Intent.ACTION_SEND)){

                movie = (Movie) it.getParcelableExtra("objeto");

            }

        }catch(Exception e) {
            Log.e("ERRO AQUI", e.getLocalizedMessage());
        }

        if(movie == null){
            Log.e("ERRO AQUI", it.getAction() + " " + it.getData());

            presenter = new DetailPresenter(this);
            presenter.getDetail(it);

        }else {
            iniViews();
        }
    }

    void iniViews(){

        tv_title = findViewById(R.id.detail_move_title);
        tv_title.setText(movie.getTitle());

        tv_description = findViewById(R.id.detail_movie_desc);
        tv_description.setText(movie.getOverView());

        movieThumbnailImg = findViewById(R.id.detail_movie_img);
        Picasso.get().
                load("https://image.tmdb.org/t/p/w342/" + movie.getPathPoster()).
                into(movieThumbnailImg);

        movieCoverImg = findViewById(R.id.detail_move_cover);
        Picasso.get().
                load("https://image.tmdb.org/t/p/w342/" + movie.getCoverPhoto()).
                into(movieCoverImg);

        movieCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));

        getSupportActionBar().setTitle(movie.getTitle());
    }

    public void onClickShareWhatsApp(View v){

        PackageManager pm = getPackageManager();
        try {
            Intent wIntent = new Intent(Intent.ACTION_SEND);
            wIntent.setType("/");
            String text = "uniritterfilmes.edu.br/filme?id=" + movie.getIdMovie();
            BitmapDrawable drawable = (BitmapDrawable) movieThumbnailImg.getDrawable();
            Bitmap b = drawable.getBitmap();
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            b.compress(Bitmap.CompressFormat.JPEG,100,bytes);
            String path = MediaStore.Images.Media.insertImage(getContentResolver(),b,"shareImage",null);
            Uri uri = Uri.parse(path);

            pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);

            wIntent.setPackage("com.whatsapp");
            wIntent.putExtra(Intent.EXTRA_TEXT,text);
            wIntent.putExtra(Intent.EXTRA_STREAM,uri);

            startActivity(wIntent);
        }catch (PackageManager.NameNotFoundException e){
            Toast.makeText(this, "WhatsApp não instalado", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showDetail(Movie movie) {

    }

    @Override
    public void showError() {
        Toast.makeText(this, "Erro ao obter filme solicitado.", Toast.LENGTH_LONG).show();
    }
}