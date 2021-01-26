package com.example.devt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;


public class UpdateUser extends AppCompatActivity {

   private ImageView profileImage;
   private Uri imageUri;
   private Button btnNext;

   FirebaseStorage storage;
    private StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        btnNext = findViewById(R.id.btnNext);
        profileImage = findViewById(R.id.userimage);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(getApplicationContext(), Menu.class));
            }
        });


        storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosePicture();
            }
        });
    }

    private void chosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==1 && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            imageUri = data.getData();
            profileImage.setImageURI(imageUri);
            UploadImage();
        }
    }

    private void UploadImage() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();


        //get id of user to create jobId
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("userPref", Context.MODE_PRIVATE);

        String UserId= sharedPreferences.getString("userId", "");


        final StorageReference riversRef = mStorageRef.child("images/"+UserId);
        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        pd.dismiss();
                        Snackbar.make(findViewById(android.R.id.content), "Image Uploade", Snackbar.LENGTH_LONG).show();

                        /*String url_Image = taskSnapshot.getStorage().toString();
                        SharedPreferences prefrenaceUser = getSharedPreferences("userImage", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefrenaceUser.edit();
                        editor.putString("urlUserImage",url_Image.toString());
                        editor.commit();*/


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        pd.dismiss();
                        Toast.makeText(UpdateUser.this, "unsuccessful uploads",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progressePercent = (100.00* snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        pd.setMessage("Percentage: "+ (int) progressePercent+"%");
                    }
                });



    }
}