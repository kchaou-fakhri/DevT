package com.example.devt;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Model.Job;

public class Emp extends AppCompatActivity {
    //references to database

    //list of
    ArrayList<Job> array = new ArrayList<Job>();

    //list of jobs
    ArrayList<Job> arrayjob=new ArrayList<Job>();
    //list of ville
    ArrayList<String> arrayville = new ArrayList<>();
    ArrayAdapter<String> adapterville;
    //list of domaine
    ArrayList<String> arraydomaine = new ArrayList<>();
    ArrayAdapter<String> adapterdomaine;
    Spinner spinnerville;
    Spinner spinnerdomaine;
    SearchView search;
    int i = 0;
    private FirebaseDatabase madatabase;
    private DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp);

        spinnerville=findViewById(R.id.spinner1);
        spinnerdomaine=findViewById(R.id.spinner2);
        adapterdomaine=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arraydomaine);
        adapterdomaine.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerdomaine.setAdapter(adapterdomaine);
        adapterville=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayville);
        adapterville.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerville.setAdapter(adapterville);
        arrayville.add("Select city");
        arraydomaine.add("Select domaine");
        //search=findViewById(R.id.search);
        madatabase=FirebaseDatabase.getInstance();
        reff=madatabase.getReference("job");
        ReadElement();

    }

    public void ReadElement()
    {
       reff.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot)
           {
               arrayjob.clear();
               for(DataSnapshot key : snapshot.getChildren())
               {

                     Job joub=key.getValue(Job.class);
                     arrayjob.add(joub);
                     if(!arrayville.contains(joub.getLocation()))
                     {
                         arrayville.add(joub.getLocation());

                     }
                     if(!arraydomaine.contains((joub.getFilde())))
                     {
                         arraydomaine.add(joub.getFilde());
                     }


               }
               adapterville.notifyDataSetChanged();
               adapterdomaine.notifyDataSetChanged();
               RecyclerView li = findViewById(R.id.rec);
               ClassAdapt cls = new ClassAdapt(arrayjob, Emp.this);
               li.setAdapter(cls);
               li.setLayoutManager(new LinearLayoutManager(Emp.this));

           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });


        spinnerville.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).toString() != "Select city") {
                    String ville = parent.getItemAtPosition(position).toString();
                    array.clear();
                    for (int i = 0; i < arrayjob.size(); i++) {
                        if(arrayjob.get(i).getLocation().equals(ville)) {
                            Job jobrecherche=arrayjob.get(i);
                            array.add(jobrecherche);
                            RecyclerView li = findViewById(R.id.rec);
                            ClassAdapt cls = new ClassAdapt(array, Emp.this);
                            li.setAdapter(cls);
                            li.setLayoutManager(new LinearLayoutManager(Emp.this));
                        }
                    }
                }
                else
                {

                    RecyclerView li = findViewById(R.id.rec);
                    ClassAdapt cls = new ClassAdapt(arrayjob, Emp.this);
                    li.setAdapter(cls);
                    li.setLayoutManager(new LinearLayoutManager(Emp.this));
                }

                }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerdomaine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (parent.getItemAtPosition(position).toString() != "Select domaine") {
                    String domaine = parent.getItemAtPosition(position).toString();
                    array.clear();
                    for (int i = 0; i < arrayjob.size(); i++) {
                        if(arrayjob.get(i).getFilde().equals(domaine)) {
                            array.add(arrayjob.get(i));
                            RecyclerView li = findViewById(R.id.rec);
                            ClassAdapt cls = new ClassAdapt(array, Emp.this);
                            li.setAdapter(cls);
                            li.setLayoutManager(new LinearLayoutManager(Emp.this));

                        }
                    }
                }
                else
                {
                    RecyclerView li = findViewById(R.id.rec);
                    ClassAdapt cls = new ClassAdapt(arrayjob, Emp.this);
                    li.setAdapter(cls);
                    li.setLayoutManager(new LinearLayoutManager(Emp.this));

                    }

                }




            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
}