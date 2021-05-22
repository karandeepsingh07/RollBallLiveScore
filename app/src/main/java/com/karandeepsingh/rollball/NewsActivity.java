package com.karandeepsingh.rollball;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewsActivity extends AppCompatActivity {

    FirebaseDatabase database;
    ProgressBar progressBar;
    long childCount;
    int ccount;
    DatabaseReference reference;
    GridView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressBar=findViewById(R.id.progressBar2);
        listView=findViewById(R.id.listviewnews);

        final PostAdapterNews adapter=new PostAdapterNews(this,R.layout.news_list);

        listView.setAdapter(adapter);


        /*for(int i=0;i<5;i++){
            NewsClass newsInfo=new NewsClass("hello","hello");
            adapter.addItem(newsInfo);
            adapter.notifyDataSetChanged();
        }*/

        progressBar.setVisibility(View.VISIBLE);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("News");
        
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                childCount=dataSnapshot.getChildrenCount();
                ccount=(int) childCount;
                ccount=Math.abs(ccount);
                Toast.makeText(NewsActivity.this, ccount+" "+childCount, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reference.orderByChild("newsId").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //progressDialog.dismiss();
                NewsClass news=dataSnapshot.getValue(NewsClass.class);
                NewsClass newsInfo=new NewsClass(news.getImgUrl(),news.getHeading());
                //   String matchId=dataSnapshot.getKey().toString();
                adapter.addItem(news);

                if(progressBar.getVisibility()==View.VISIBLE)
                    progressBar.setVisibility(View.GONE);
              //  Collections.reverse(listItems);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
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
