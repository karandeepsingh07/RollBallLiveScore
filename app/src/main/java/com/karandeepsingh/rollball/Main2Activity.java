package com.karandeepsingh.rollball;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/*import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayerView;*/

public class Main2Activity extends AppCompatActivity{

    FirebaseDatabase database;
    DatabaseReference reference,reference2;
    TextView t1,t2,t3,t4,t8,t9,t5,esc1,esc2,t17;
    TextView half1sctext,half1sc2text,half2sc1text,half2sc2text;
    ImageView i1;

    //YouTubePlayer livePlayer;
    //YouTubePlayerView playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bundle = getIntent().getExtras();
        String getting = bundle.getString("Match");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Match");

        reference2 = reference.child(getting);


        t1= (TextView) findViewById(R.id.textView);
        t2= (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView3);
        t4 = (TextView) findViewById(R.id.textView4);
        t5=findViewById(R.id.winteam);
        t8 = (TextView) findViewById(R.id.textView8);
        t9 = (TextView) findViewById(R.id.textView9);
        t17=findViewById(R.id.textView17);
        esc1=findViewById(R.id.esc1);
        esc2=findViewById(R.id.esc2);
        i1=findViewById(R.id.imageView3);
        half1sctext=findViewById(R.id.halfsc1);
        half1sc2text=findViewById(R.id.halfsc2);
        half2sc1text=findViewById(R.id.half2sc1);
        half2sc2text=findViewById(R.id.half2sc2);


        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Score score=dataSnapshot.getValue(Score.class);
                String s = score.getTeam1Name();
                String s1 = score.getTeam2Name();
                int s3 = score.getTeam1Foul();
                int s4 = score.getTeam2Foul();
                int es1= score.getEscore1();
                int es2= score.getEscore2();
                int s5 = score.getTeam1Score();
                int s6 = score.getTeam2Score();
                int status=score.getStatus();
                String half=score.getHalf();
                String url=score.getImageUrl();
                //   Toast.makeText(Main2Activity.this, ""+s1, Toast.LENGTH_SHORT).show();
                t1.setText(s);
                t2.setText(s1);
                t3.setText(Integer.toString(s5));
                t4.setText(Integer.toString(s6));
                half1sctext.setText(Integer.toString(score.getHalfsc1()));
                half1sc2text.setText(Integer.toString(score.getHalfsc2()));
                half2sc1text.setText(Integer.toString(score.getHalf2sc1()));
                half2sc2text.setText(Integer.toString(score.getHalf2sc2()));
                t17.setText("Extra Time Score");
                esc1.setText(Integer.toString(es1));
                esc2.setText(Integer.toString(es2));

                if(score.getStatus()==0)
                    t5.setText("Live");
                else if(score.getStatus()==2){
                    if((score.getTeam1Score()+score.getEscore1())>(score.getTeam2Score()+score.getEscore2())){
                        t5.setText("Won by "+score.getTeam1Name());
                    }
                    else if((score.getTeam1Score()+score.getEscore1())==(score.getTeam2Score()+score.getEscore2()))
                        t5.setText("MatchDrawn");

                    if((score.getTeam1Score()+score.getEscore1())<(score.getTeam2Score()+score.getEscore2())){
                        t5.setText("Won by "+score.getTeam2Name());
                    }
                }
                else if(score.getStatus()==1)
                    t5.setText("Upcoming");

                t8.setText(Integer.toString(s3));
                t9.setText(Integer.toString(s4));

                Picasso.get().load(url).into(i1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
