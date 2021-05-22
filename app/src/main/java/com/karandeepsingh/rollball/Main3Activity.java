package com.karandeepsingh.rollball;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Main3Activity extends AppCompatActivity {

    FirebaseDatabase database;
    ProgressBar progressBar;
    DatabaseReference reference,reference2;
    ImageView newsimg;
    TextView headtext,mattertext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();
        String getting = bundle.getString("News");

        progressBar=findViewById(R.id.progressBar2);
        headtext=findViewById(R.id.headtext);
        mattertext=findViewById(R.id.matertext);
        newsimg=findViewById(R.id.newsimg);

        TournamentInfo tour=new TournamentInfo();

        progressBar.setVisibility(View.VISIBLE);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("News");

        reference2 = reference.child(getting);

        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                NewsClass news=dataSnapshot.getValue(NewsClass.class);
                try {
                    String head = news.getHeading();
                    String url=news.getImgUrl();
                    String matter=news.getMatter();

                    headtext.setText(head);
                    mattertext.setText(matter);
                    Picasso.get().load(url).into(newsimg);
                    if(progressBar.getVisibility()==View.VISIBLE)
                        progressBar.setVisibility(View.GONE);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
