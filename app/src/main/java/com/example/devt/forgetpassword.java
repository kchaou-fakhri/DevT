package com.example.devt;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetpassword extends AppCompatActivity {
    Button next ;
    EditText Email;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        next = findViewById(R.id.Next);
        Email =findViewById(R.id.email);
        auth= FirebaseAuth.getInstance();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    reset();
            }
        });
    }
    public void reset()
    {
        String email1 =Email.getText().toString().trim();
        if(email1.isEmpty())
        {   Email.setError("Email is required");
            Email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
            Email.setError("please provide valid email");
            Email.requestFocus();

        }
        auth.sendPasswordResetEmail(email1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
           if(task.isSuccessful())
           {
               Toast.makeText(forgetpassword.this,"check your email to reset your password",Toast.LENGTH_SHORT).show();
           }else{
               Toast.makeText(forgetpassword.this,"Try again! Something wrong happened",Toast.LENGTH_SHORT).show();

           }
            }
        });
    }
}