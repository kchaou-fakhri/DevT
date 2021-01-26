package com.example.devt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class PageDetaile extends AppCompatActivity implements View.OnClickListener {
    String mail;
    ImageView model_user_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_detaile);

        TextView titre=(TextView)findViewById(R.id.titre);
        TextView ville=(TextView)findViewById(R.id.vill);
        TextView desciption=(TextView)findViewById(R.id.desc);
        model_user_image = findViewById(R.id.model_user_image);
        Button btpostuler=(Button)findViewById(R.id.btn1);
        Intent intf =getIntent();
        String tit=intf.getStringExtra("tit");
        String vil=intf.getStringExtra("vil");
        String descr=intf.getStringExtra("description");
        String user_id=intf.getStringExtra("userId");
        mail=intf.getStringExtra("mail");
        desciption.setText(descr);
        titre.setText(tit);
        ville.setText(vil);

        btpostuler.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",mail,null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        startActivity(emailIntent);
    }


}