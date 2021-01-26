package Model.DAO;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.devt.Profile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.UploadTask;

import Model.User;

public class DAOUser
{
    private DatabaseReference reference;

   public User FindUserById(String _id)
   {
       final User user = null;

       TextView profile_email, profile_name, profile_type, profile_facebook;

       reference = FirebaseDatabase.getInstance().getReference().child("/users").child(_id);
       reference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               String name = snapshot.child("username").getValue().toString();
               String type = snapshot.child("type").getValue().toString();
               String email = snapshot.child("email").getValue().toString();
               String password = snapshot.child("password").getValue().toString();


             user.setUsername(name);
             user.setType(type);
             user.setEmail(email);
             user.setPassword(password);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

       return user;
   }


}
