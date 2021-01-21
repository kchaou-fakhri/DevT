package com.example.devt;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PageDetaile extends AppCompatActivity implements View.OnClickListener {
    String mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_detaile);

        TextView titre=(TextView)findViewById(R.id.titre);
        TextView ville=(TextView)findViewById(R.id.vill);
        TextView desciption=(TextView)findViewById(R.id.desc);
        Button btpostuler=(Button)findViewById(R.id.btn1);
        Intent intf =getIntent();
        String tit=intf.getStringExtra("tit");
        String vil=intf.getStringExtra("vil");
        String descr=intf.getStringExtra("description");
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