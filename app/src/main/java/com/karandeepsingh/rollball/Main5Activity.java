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

public class Main5Activity extends AppCompatActivity {

    ProgressBar progressBar;
    FirebaseDatabase database;
    DatabaseReference reference,reference2;
    ImageView ifixture;
    TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressBar=findViewById(R.id.progressBar2);

        Bundle bundle = getIntent().getExtras();
        String getting = bundle.getString("Match");

        ifixture=findViewById(R.id.fixture);
        tx=findViewById(R.id.ftext);

        progressBar.setVisibility(View.VISIBLE);

        TournamentInfo tour=new TournamentInfo();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Tournament");

        reference2 = reference.child(getting);

        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TournamentInfo team=dataSnapshot.getValue(TournamentInfo.class);
                try {
                    String xyz = team.getTeamsParticipating();
                    String url=team.getImageUrl();
                    Picasso.get().load(url).into(ifixture);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        tx.setVisibility(View.GONE);
        if(progressBar.getVisibility()==View.VISIBLE) {
            progressBar.setVisibility(View.GONE);
            ifixture.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
