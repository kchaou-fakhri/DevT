package com.example.devt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Model.DAO.DAOUser;
import Model.User;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Profile extends AppCompatActivity {

    ImageView profile_image;
    ImageView img2;

    TextView profile_email, profile_name, profile_type, profile_facebook;
    DAOUser daoUser = new DAOUser();
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);





        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("userPref", Context.MODE_PRIVATE);


        String userId = sharedPreferences.getString("userId", "");


     //  user = daoUser.FindUserById(userId);


        profile_email = findViewById(R.id.profile_email);
        profile_name = findViewById(R.id.profile_username);
        profile_type = findViewById(R.id.profile_type);
        profile_facebook = findViewById(R.id.profile_facebook);
        profile_image = findViewById(R.id.profile_image);





        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("/users").child(userId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("username").getValue().toString();
                String type = snapshot.child("type").getValue().toString();
                String email = snapshot.child("email").getValue().toString();
                String password = snapshot.child("password").getValue().toString();

                profile_email.setText(email);
                profile_type.setText(type);
                profile_name.setText(name);
                profile_facebook.setText("facebook.com/"+name);

                Toast.makeText(Profile.this, name, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        SharedPreferences sharedPreferences1 = getApplicationContext().getSharedPreferences("userImage", Context.MODE_PRIVATE);

        String UserImage = sharedPreferences1.getString("urlUserImage", "");


        getUrlAsync();

    }



    private void getUrlAsync (){
        // Points to the root reference
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("userPref", Context.MODE_PRIVATE);


        String userId = sharedPreferences.getString("userId", "");

        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference dateRef = storageRef.child("/images").child(userId);
        dateRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
        {
            @Override
            public void onSuccess(Uri downloadUrl)
            {
                Picasso.with(Profile.this)
                        .load(downloadUrl.toString())
                        .transform(new CircleTransform()).into(profile_image);

            }
        });
    }
}