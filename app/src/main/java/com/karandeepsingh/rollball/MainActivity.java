
package com.karandeepsingh.rollball;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabHost tb;
    EditText e1, e2;
    //Button b1,b2;
    ImageView imageView;
    TextView team, s1, team2, s2, f1, f2;
    FirebaseDatabase database;
    WebView browser, browser2;
    DatabaseReference reference;
    Button b1;
    private ProgressDialog progressDialog;


    RecyclerView recyclerView, recyclerView2;
    RecyclerView.Adapter adapter, adapter2;
    //MyAdapter mAdapter;
    List<Score> listItems, listItems2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

       /* tb= findViewById(R.id.tabhost);
        tb.setup();


        this.setNewTab(this, tb, "tab1", R.string.tab1title, R.drawable.homeicn, R.id.tab1);
        */
/*b1=findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notific();
            }
        });*//*



        this.setNewTab(this, tb, "tab2", R.string.tab2title, R.drawable.matchicn, R.id.tab2);
        */
/*TabHost.TabSpec tabSpec=tb.newTabSpec("");
        tabSpec.setIndicator("Home");
        tabSpec.setContent(R.id.tab1);
        tb.addTab(tabSpec);*//*


        */
/*TabHost.TabSpec tabSpec=tb.newTabSpec("");
        tabSpec.setIndicator("Live Score");
        tabSpec.setContent(R.id.tab2);
        tb.addTab(tabSpec);*//*




        */
/*TabHost.TabSpec tabSpec=tb.newTabSpec("");
        tabSpec.setIndicator("Info");
        tabSpec.setContent(R.id.tab3);
        tb.addTab(tabSpec);*//*

        this.setNewTab(this, tb, "tab2", R.string.tab3title, R.drawable.infoicn, R.id.tab3);

        browser = (WebView) findViewById(R.id.tab3);
        browser.loadUrl("https://www.rollball.org/");

        */
/*tabSpec=tb.newTabSpec("");
        tabSpec.setIndicator("Registration");
        tabSpec.setContent(R.id.tab4);
        tb.addTab(tabSpec);*//*

        this.setNewTab(this, tb, "tab2", R.string.tab4title, R.drawable.registrationicn, R.id.tab4);

        browser2 = findViewById(R.id.tab4);
        browser2.loadUrl("http://rollballregistration.org");


        */
/*recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressDialog=new ProgressDialog(this);
        listItems= new ArrayList<>();

        adapter=new PostAdapter((ArrayList<Score>) listItems,this);

        recyclerView.setAdapter(adapter);*//*





        recyclerView2= findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        listItems2= new ArrayList<>();

        adapter2=new PostAdapter((ArrayList<Score>) listItems2,this);

        recyclerView2.setAdapter(adapter2);

        for (int i=0;i<5;i++){
            Score scoreN=new Score();
            listItems2.add(scoreN);
            adapter2.notifyDataSetChanged();
        }

        */
/*Bitmap bmp = null;
        try {
            URL url = new URL(new Score().getImageUrl());
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }*//*


        */
/*imageView=findViewById(R.id.imageView);
        String url="https://www.w3schools.com/w3css/img_lights.jpg";
        new DownloadImageTask(imageView).execute(url);
        //Picasso.get().load("https://www.w3schools.com/w3css/img_lights.jpg").into(imageView);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Match");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                progressDialog.dismiss();
                Score score = dataSnapshot.getValue(Score.class);
             //   String matchId=dataSnapshot.getKey().toString();
                listItems.add(score);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                Score newScore=dataSnapshot.getValue(Score.class);
                    if (newScore != null) {
                        for (int i = 0; i < listItems.size(); i++) {
                            if (listItems.get(i).getMatchId().equals(key)) {
                                listItems.set(i,newScore);
//                                listItems.remove(i);
  //                              listItems.add(newScore);
                            }
                        }
                    }
                    adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                    for (int i = 0; i < listItems.size(); i++) {
                        if (listItems.get(i).getMatchId().equals(key)) {
                                listItems.remove(i);
                        }
                    }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*//*


        //FirebaseStorage storage=FirebaseStorage.getInstance();
        //StorageReference storageRef = storage.getReference("GameImages/image1.jpg");



// Create a reference with an initial file path and name




//original
        */
/*team= (TextView) findViewById(R.id.textView2);
        team2= (TextView) findViewById(R.id.textView);
        s1= (TextView) findViewById(R.id.textView3);
        s2= (TextView) findViewById(R.id.textView4);
        f1= (TextView) findViewById(R.id.textView8);
        f2= (TextView) findViewById(R.id.textView9);

//        b1= (Button) findViewById(R.id.button2);
        //b2= (Button) findViewById(R.id.button3);

        database= FirebaseDatabase.getInstance();

        reference=database.getReference("Score_Updates");

        /*b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String team=e1.getText().toString();
                String score=e2.getText().toString();

                Score score1=new Score();
                score1.setTeamName(team);
                score1.setTeamScore(score);

                reference.setValue(score1);

                e1.setText("");
                e2.setText("");
                Toast.makeText(MainActivity.this, "Record Updated", Toast.LENGTH_SHORT).show();
            }
        });*//*

        */
/*b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {*//*


              //original
               */
/* reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot datasnapshot)
                    {
                        Score score=datasnapshot.getValue(Score.class);
                        String t=score.getTeam1Name();
                        String s=score.getTeam1Score();
                        team.setText(""+score.getTeam1Name());
                        team2.setText(""+score.getTeam2Name());
                        s1.setText(""+score.getTeam1Score());
                        s2.setText(""+score.getTeam2Score());
                        f1.setText(""+score.getTeam1Foul());
                        f2.setText(""+score.getTeam2Foul());

                        Toast.makeText(MainActivity.this,"Score Updated", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
           // }
       // });*//*

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        switch(id){
            case R.id.item1:
                Intent intent1=new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNewTab(Context context, TabHost tabHost, String tag, int title, int icon, int contentID ){
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);
        tabSpec.setIndicator(getTabIndicator(tabHost.getContext(), title, icon)); // new function to inject our own tab layout
        tabSpec.setContent(contentID);
        tabHost.addTab(tabSpec);
    }

    private View getTabIndicator(Context context, int title, int icon) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
        ImageView iv = view.findViewById(R.id.imageViewtab);
        iv.setImageResource(icon);
        TextView tv = view.findViewById(R.id.textViewtab);
        tv.setText(title);
        return view;
    }

    private void notific(){

        */
/*try {
            URL url = new URL("https://www.w3schools.com/w3css/img_lights.jpg");
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }*//*


        NotificationCompat.BigPictureStyle bigPictureStyle=new NotificationCompat.BigPictureStyle();
        bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.splash)).build();


        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent resultIntent=new Intent(this,MainActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,(int) Calendar.getInstance().getTimeInMillis(),resultIntent,0);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.registrationicn)
                .setContentTitle("Match")
                .setContentText("BIgStyle NOtification")
                .setContentIntent(pendingIntent)
                .setStyle(bigPictureStyle)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setDefaults(Notification.DEFAULT_ALL);
        notificationManager.notify(0,builder.build());
    }


}
*/
