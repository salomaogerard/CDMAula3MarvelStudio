package com.example.cdmaula3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView botaoCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        botaoCadastro = findViewById(R.id.register);
            botaoCadastro.setOnClickListener(new View.OnClickListener(){
                @Override
                    public void onClick(View v){
                        Intent it = new Intent(MainActivity.this, RegisterActivity.class);
                        startActivity(it);
                }
        });
    }
}