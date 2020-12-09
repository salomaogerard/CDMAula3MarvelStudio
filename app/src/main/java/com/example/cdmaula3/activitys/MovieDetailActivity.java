package com.example.cdmaula3.activitys;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
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

import static android.hardware.Sensor.TYPE_LIGHT;

public class MovieDetailActivity extends AppCompatActivity implements DetailContract.ListDetailView {

    public static final String INFO_MOVIE = "INFO_MOVIE";
    private ImageView movieThumbnailImg, movieCoverImg;
    private TextView tv_title, tv_description;
    private Movie movie;
    private DetailContract.ListDetailPresenter presenter;
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private SensorEventListener lightEventListener;
    private View root;
    private float maxValue;
    private TextView movieDesc, shareText, movieTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        root = findViewById(R.id.root);
        movieDesc = findViewById(R.id.detail_movie_desc);
        shareText = findViewById(R.id.text_share);
        movieTitle = findViewById(R.id.detail_move_title);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(TYPE_LIGHT);
            if(lightSensor == null){
                Toast.makeText(this,"Dispositivo não possui sensor de luminosidade.",Toast.LENGTH_SHORT).show();
            }
        maxValue = lightSensor.getMaximumRange();
            lightEventListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    float value = event.values[0];

                        int nValor = (int) (255f * value/maxValue);
                        int vValor = (int) (255f / value / maxValue);
                        root.setBackgroundColor(Color.rgb(nValor,nValor,nValor));
                        movieTitle.setTextColor(Color.rgb(vValor,vValor,vValor));
                        movieDesc.setTextColor(Color.rgb(vValor,vValor,vValor));
                        shareText.setTextColor(Color.rgb(vValor,vValor,vValor));

                }
                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };


        Intent it = getIntent();

        try {
            if(it.getAction().equals(Intent.ACTION_SEND)){

                movie = it.getParcelableExtra("objeto");

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

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(lightEventListener,lightSensor,SensorManager.SENSOR_DELAY_FASTEST);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(lightEventListener);
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