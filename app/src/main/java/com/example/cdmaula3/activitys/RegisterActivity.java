package com.example.cdmaula3.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cdmaula3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity  implements  View.OnClickListener{

    private EditText editText_Email, editText_Password, editText_ConfirmPassword;
    private Button button_Register, button_Cancel;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        editText_Email = (EditText)findViewById(R.id.registerEmail);
        editText_Password = (EditText)findViewById(R.id.registerPassword);
        editText_ConfirmPassword = (EditText)findViewById(R.id.registerConfirmPassword);
        button_Register = (Button)findViewById(R.id.buttonSignUp);
        button_Cancel = (Button)findViewById(R.id.buttonCancel);

        button_Register.setOnClickListener(this);
        button_Cancel.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.buttonSignUp:
                register();
                break;
            case R.id.buttonCancel:
                cancel();
                break;
        }
    }

    private void register(){
        String email = editText_Email.getText().toString().trim();
        String password = editText_Password.getText().toString().trim();
        String confirmPassword = editText_ConfirmPassword.getText().toString().trim();

        if(email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            Toast.makeText(getBaseContext(), "ERRO: Preencha todos os campos", Toast.LENGTH_LONG).show();
        }else{
            if(password.contentEquals(confirmPassword)){
                createNewUser(email, password);
            }else{
                Toast.makeText(getBaseContext(), "ERRO: Senhas não coincidem", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void createNewUser(final String email, final String password){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent it = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(it);
                    Toast.makeText(getBaseContext(), "Cadastro efetuado com Sucesso", Toast.LENGTH_LONG).show();
                }else if(task.getException().toString().equalsIgnoreCase(getString(R.string.error_account_already_exists))){
                    Toast.makeText(getBaseContext(), "O endereço de email já está sendo usado! Logue e vincule na aba Usuario!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getBaseContext(), "Erro ao Cadastrar " + task.getException(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void cancel(){
        Intent it = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(it);
    }
}