package com.example.cdmaula3.activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cdmaula3.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_Email, editText_Password;
    private Button button_LoginEmail, button_LoginGoogle;
    private TextView button_Register, button_RecoverPassword;

    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth firebaseAuth;

    private int RC_SIGN_IN = 1;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        editText_Email = (EditText)findViewById(R.id.email);
        editText_Password = (EditText)findViewById(R.id.password);
        button_LoginGoogle = (Button)findViewById(R.id.buttonSignInGoogle);
        button_LoginEmail = (Button)findViewById(R.id.buttonSignInEmail);
        button_RecoverPassword = (TextView)findViewById(R.id.forgetPassword);
        button_Register = (TextView)findViewById(R.id.register);

        button_LoginGoogle.setOnClickListener(this);
        button_LoginEmail.setOnClickListener(this);
        button_RecoverPassword.setOnClickListener(this);
        button_Register.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonSignInGoogle:
                signInWithGoogle();
                break;
            case R.id.buttonSignInEmail:
                signInWithEmail();
                break;
            case R.id.forgetPassword:
                forgetPassword();
                break;
            case R.id.register:
                register();
                break;
        }
    }

    private void forgetPassword(){
        Intent it = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
        startActivity(it);
    }

    private void register(){
        Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(it);
    }

    private void signInWithEmail(){
        String email = editText_Email.getText().toString().trim();
        String password = editText_Password.getText().toString().trim();

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(getBaseContext(), "Preencha todos os campos", Toast.LENGTH_LONG).show();
        }else{
            confirmLoginEmail(email, password);
        }
    }

    private void confirmLoginEmail(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent it = new Intent(LoginActivity.this, FirstPageActivity.class);
                    startActivity(it);
                    Toast.makeText(getBaseContext(), "Logado com sucesso", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getBaseContext(), "Erro ao logar", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void signInWithGoogle(){
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try{
            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
            FirebaseGoogleAuth(acc);
        }catch (ApiException e){
            Toast.makeText(LoginActivity.this, "Erro ao logar", Toast.LENGTH_LONG).show();
            FirebaseGoogleAuth(null);
        }
    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acct){
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent it = new Intent(LoginActivity.this, FirstPageActivity.class);
                    startActivity(it);
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    updateUI(user);
                }else{
                    Toast.makeText(LoginActivity.this, "Erro ao logar", Toast.LENGTH_LONG).show();
                    updateUI(null);
                }
            }
        });
    }

    private void updateUI(FirebaseUser fUser){
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account != null){
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();

            Toast.makeText(LoginActivity.this, "Bem vindo " + personName, Toast.LENGTH_SHORT).show();
        }
    }

}