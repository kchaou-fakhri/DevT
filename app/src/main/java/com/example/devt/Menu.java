package com.example.devt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Menu extends AppCompatActivity {

   RelativeLayout jobsList, profile, insertJob, settingMenu, logout, editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        editProfile = findViewById(R.id.edit_profile);
        jobsList = findViewById(R.id.joblist);
        profile  = findViewById(R.id.profile);
        insertJob= findViewById(R.id.insertJob);
        settingMenu = findViewById(R.id.settingsmenu);
        logout   = findViewById(R.id.logout);

        jobsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Emp.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Profile.class);
                startActivity(intent);
            }
        });

        insertJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, InsertJob.class);
                startActivity(intent);
            }
        });


        settingMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, UpdateUser.class);
                startActivity(intent);
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, UpdateUser.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getApplicationContext().getSharedPreferences("userPref", 0).edit().clear().commit();


                Intent intent = new Intent(Menu.this, Login.class);
                startActivity(intent);
            }
        });



    }
}