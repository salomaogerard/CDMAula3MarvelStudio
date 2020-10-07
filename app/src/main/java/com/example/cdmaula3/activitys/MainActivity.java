package com.example.cdmaula3.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cdmaula3.R;

public class MainActivity extends AppCompatActivity {

    private TextView buttonRegister, buttonSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        buttonRegister = findViewById(R.id.register);
        buttonRegister.setOnClickListener(new View.OnClickListener(){
                @Override
                    public void onClick(View v){
                        Intent it = new Intent(MainActivity.this, RegisterActivity.class);
                        startActivity(it);
                }
        });

        buttonSignIn = findViewById(R.id.botaoSignIn);
        buttonSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent it = new Intent(MainActivity.this, FirstPageActivity.class);
                startActivity(it);
            }
        });
    }
}