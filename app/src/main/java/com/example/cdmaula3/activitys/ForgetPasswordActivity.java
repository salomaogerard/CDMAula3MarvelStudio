package com.example.cdmaula3.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cdmaula3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_Email;
    private Button button_RecoverPassword, button_Cancel;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        getSupportActionBar().hide();

        editText_Email = (EditText)findViewById(R.id.emailRecoverPassword);
        button_RecoverPassword = (Button)findViewById(R.id.buttonRecoverPassword);
        button_Cancel = (Button)findViewById(R.id.buttonCancelRecoverPassword);

        button_RecoverPassword.setOnClickListener(this);
        button_Cancel.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonRecoverPassword:
                forgetPassword();
                break;
            case R.id.buttonCancelRecoverPassword:
                cancel();
                break;
        }
    }

    private void forgetPassword(){
        String email = editText_Email.getText().toString().trim();
        if(email.isEmpty()){
            Toast.makeText(getBaseContext(), "Preencha todos os campos", Toast.LENGTH_LONG).show();
        }else{
            confirmRecoverPassword(email);
        }
    }

    private void confirmRecoverPassword(String email){
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getBaseContext(), "Cheque seu email para continuar a alterar sua senha!", Toast.LENGTH_LONG).show();
                    Intent it = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                    startActivity(it);
                }else{
                    String message = task.getException().getMessage();
                    Toast.makeText(getBaseContext(), "Erro: " + message, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void cancel(){
        Intent it = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
        startActivity(it);
    }
}