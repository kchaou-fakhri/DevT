package com.example.devt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.User;

public class SignUp extends AppCompatActivity {

    EditText username, email, password;
    Button login, btnCreate;
    RadioButton employee, employeur;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.newusername);
        email = findViewById(R.id.newemail);
        password = findViewById(R.id.newpassword);
        employee = findViewById(R.id.employee);
        employeur = findViewById(R.id.employeur);



        login = findViewById(R.id.exist);
        btnCreate = findViewById(R.id.createNew);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (TextUtils.isEmpty(username.getText())) {
                    username.setError("user name is required");
                    return;
                }
                if (TextUtils.isEmpty(email.getText())) {
                    email.setError("email is required");
                    return;
                }
                if (TextUtils.isEmpty(password.getText())) {
                    password.setError("password is required");
                    return;
                }
                if (password.length() < 6) {
                   password.setError("password Must be >=6 Charactere ");
                    return;
                }
                final FirebaseAuth fAuth = FirebaseAuth.getInstance();
                fAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {


                            CreateUser(fAuth);


                            Toast.makeText(SignUp.this, "User created", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(SignUp.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }


                });


                // Write a message to the database

           }
        });




    }


    public void CreateUser(FirebaseAuth fAuth)
    {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        String type = "";
        if(employee.isChecked())
        {
            type = "employee";
        }
        else
        {
            type = "employeur";
        }

        User user =  new User(username.getText().toString(), email.getText().toString(), password.getText().toString(), type);

        myRef.child(fAuth.getCurrentUser().getUid()).setValue(user);
        Intent intent = new Intent(SignUp.this, Login.class);

        startActivity(intent);
    }
}