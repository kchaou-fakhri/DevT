package com.example.devt;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Model.job;

public class ClassAdapt extends RecyclerView.Adapter<ClassAdapt.ViewHolder> {
    private ArrayList<job> ListJob;
    Context context;
    public ClassAdapt(ArrayList<job> list,Context cnx)
    {
        ListJob=list;
        context=cnx;

        //new ViewHolder(R.layout.modelepresentation);
    }




    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        LayoutInflater inflayter = LayoutInflater.from(parent.getContext());
        View cc = inflayter.inflate(R.layout.modelepresentation, parent, false);
        return new ViewHolder(cc);
    }


    public void onBindViewHolder(@NonNull ClassAdapt.ViewHolder holder, int position)
    {
        job Job =ListJob.get(position);
        holder.ville.setText(Job.getVille());
        holder.titre.setText(Job.getTitre());
        holder.titreView.setText(Job.getTitre());
        getUrlAsync(Job.getUserId(), holder.model_image_user);

        holder.jobis=Job;
    }



    @Override
    public int getItemCount()
    {
        return (ListJob.size());
    }

    public class ViewHolder extends  RecyclerView.ViewHolder
    {

        private job jobis;
        TextView titre,ville, titreView;
        ImageView model_image_user;
        private LinearLayout linear;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linear=itemView.findViewById(R.id.l);
            titre=itemView.findViewById(R.id.emp);
            ville=itemView.findViewById(R.id.vill);
            titreView = itemView.findViewById(R.id.titre_listView);
            model_image_user = itemView.findViewById(R.id.model_user_image);

            linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String domaine=jobis.getDomaine();
                    String tit=jobis.getTitre();
                    String ville=jobis.getVille();
                    String mail=jobis.getMail();
                    String description=jobis.getDesc();
                    String userId = jobis.getUserId();
                    Intent intent = new Intent(context,PageDetaile.class);
                    intent.putExtra("dom",domaine);
                    intent.putExtra("vil",ville);
                    intent.putExtra("tit",tit);
                    intent.putExtra("mail",mail);
                    intent.putExtra("description",description);
                    intent.putExtra("userId",userId );
                    context.startActivity(intent);


                }
            });


        }


    }

    private void getUrlAsync (String userId, final ImageView model_user_image){


        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference dateRef = storageRef.child("/images").child(userId);
        dateRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
        {
            @Override
            public void onSuccess(Uri downloadUrl)
            {
                Picasso.with(context)
                        .load(downloadUrl.toString())
                        .transform(new CircleTransform()).into(model_user_image);

            }
        });
    }
}
