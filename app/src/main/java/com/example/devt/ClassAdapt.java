package com.example.devt;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import Model.Job;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassAdapt extends RecyclerView.Adapter<ClassAdapt.ViewHolder> {
    private ArrayList<Job> ListJob;
    Context context;
    public ClassAdapt(ArrayList<Job> list,Context cnx)
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


    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Job job =ListJob.get(position);
        holder.ville.setText(job.getLocation());
        holder.titre.setText(job.getTitle());
        holder.jobis=job;
    }



    @Override
    public int getItemCount()
    {
       return (ListJob.size());
    }

    public class ViewHolder extends  RecyclerView.ViewHolder
    {

        private Job jobis;
        TextView titre,ville;
        private LinearLayout linear;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linear=itemView.findViewById(R.id.l);
            titre=itemView.findViewById(R.id.emp);
            ville=itemView.findViewById(R.id.vill);
            linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String domaine=jobis.getFilde();
                    String tit=jobis.getTitle();
                    String ville=jobis.getLocation();
                    String mail=jobis.getMail();
                    String description=jobis.getDescription();
                    Intent intent = new Intent(context, PageDetaile.class);
                    intent.putExtra("dom",domaine);
                    intent.putExtra("vil",ville);
                    intent.putExtra("tit",tit);
                    intent.putExtra("mail",mail);
                    intent.putExtra("description",description);
                    context.startActivity(intent);


                }
            });


        }


    }
}
