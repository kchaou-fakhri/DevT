package com.example.devt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    ImageView image;
    Button btnsign, btnLogin;
    TextView tv1, tv2;
    TextInputLayout email, password;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        image = findViewById(R.id.mlogo);
        btnsign = findViewById(R.id.create);
        btnLogin = findViewById(R.id.btnlogin);
        tv1 = findViewById(R.id.textlogo);
        tv2 = findViewById(R.id.slogen);
        email = findViewById(R.id.username);
        password = findViewById(R.id.password);
        fAuth=FirebaseAuth.getInstance();




        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this, SignUp.class);
                Pair[] pairs = new Pair[7];

                pairs[0] = new Pair<View, String>(image, "uplogo");
                pairs[1] = new Pair<View, String>(email, "upusername");
                pairs[2] = new Pair<View, String>(password, "uppassword");
                pairs[3] = new Pair<View, String>(tv1, "uptextlogo");
                pairs[4] = new Pair<View, String>(tv2, "upslogon");
                pairs[5] = new Pair<View, String>(btnsign, "signup");
                pairs[6] = new Pair<View, String>(btnLogin, "login");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                startActivity(intent, options.toBundle());

            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String validationEmail = String.valueOf(email.getEditText().getText());
                String validationPassword = String.valueOf(password.getEditText().getText());
                if (TextUtils.isEmpty(validationEmail)) {
                    email.setError("email is required");
                    return;
                }
                if (TextUtils.isEmpty(validationPassword
                )) {
                    password.setError("password is required");
                    return;
                }
                if (password.toString().trim().length() < 6) {
                    password.setError("password Must be >=6 Charactere ");
                    return;
                }

                fAuth.signInWithEmailAndPassword(validationEmail, validationPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "User connected", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Menu.class));

                        } else {

                            Toast.makeText(Login.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });
    }



}