package com.karandeepsingh.rollball;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class HomeFragment extends Fragment {

    private Button b1;
    ImageView itwit,ifb,iinsta,iyt;
    private CardView ib1;
    private RecyclerView recyclerView,recyclerView2;
    private List<TournamentInfo> listItems;
    private RecyclerView.Adapter adapter,adapter2;
    FirebaseDatabase database;
    DatabaseReference reference;
    private TextView hoardingText,titleText,newsText;
    GridLayoutManager gridLayoutManager;

    FirebaseDatabase database2;
    ProgressBar progressBar;
    long childCount;
    int ccount;
    DatabaseReference reference2;
    GridView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final FrameLayout mFrameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_home,
                container, false);
        /*b1=view.findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notific();
                Toast.makeText(getActivity(), "gottcha", Toast.LENGTH_SHORT).show();
            }
        });*/

        View view=inflater.inflate(R.layout.fragment_home, container, false);
        hoardingText=view.findViewById(R.id.hoarding);
        titleText=view.findViewById(R.id.tournamenttitle);

        recyclerView= view.findViewById(R.id.recyclerViewTour);
        recyclerView.setHasFixedSize(true);


        gridLayoutManager=new GridLayoutManager(getContext(),1,GridLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);


        listItems= new ArrayList<>();

        adapter=new PostAdapterTour((ArrayList<TournamentInfo>) listItems,getContext());


        recyclerView.setAdapter(adapter);



        //News list code
        progressBar=view.findViewById(R.id.progressBar2);
        listView=view.findViewById(R.id.listviewnews);

        final PostAdapterNews adapterNews=new PostAdapterNews(getContext(),R.layout.news_list);

        listView.setAdapter(adapterNews);


        /*for(int i=0;i<5;i++){
            NewsClass newsInfo=new NewsClass("hello","hello");
            adapter.addItem(newsInfo);
            adapter.notifyDataSetChanged();
        }*/

        progressBar.setVisibility(View.VISIBLE);

        database2 = FirebaseDatabase.getInstance();
        reference2 = database2.getReference("News");

        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                childCount=dataSnapshot.getChildrenCount();
                ccount=(int) childCount;
                ccount=Math.abs(ccount);
                //Toast.makeText(getContext(), ccount+" "+childCount, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reference2.orderByChild("newsId").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //progressDialog.dismiss();
                NewsClass news=dataSnapshot.getValue(NewsClass.class);
                NewsClass newsInfo=new NewsClass(news.getImgUrl(),news.getHeading());
                //   String matchId=dataSnapshot.getKey().toString();
                adapterNews.addItem(news);

                if(progressBar.getVisibility()==View.VISIBLE)
                    progressBar.setVisibility(View.GONE);
                //  Collections.reverse(listItems);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                adapterNews.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adapterNews.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //News list code ends

        /*ib1=view.findViewById(R.id.buttonNews);
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),NewsActivity.class);
                startActivity(intent);
            }
        });*/

        ifb=view.findViewById(R.id.facebooklogo);
        ifb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("http://facebook.com/RollBallFederation/?ti=as");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                intent.setPackage("com.facebook.katana");
                try{
                    startActivity(intent);
                }catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://facebook.com/RollBallFederation/?ti=as")));
                }
            }
        });
        itwit=view.findViewById(R.id.twitterlogo);
        itwit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("http://twitter.com/official_irbf?s=09");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                intent.setPackage("com.twitter.android");
                try{
                    startActivity(intent);
                }catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://twitter.com/official_irbf?s=09")));
                }
            }
        });
        iinsta=view.findViewById(R.id.instagramlogo);
        iinsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("http://instagram.com/rollballofficial?utm_source=ig_profile_share&igshid=mljqfwrixke3");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                intent.setPackage("com.instagram.android");
                try{
                    startActivity(intent);
                }catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://instagram.com/rollballofficial?utm_source=ig_profile_share&igshid=mljqfwrixke3")));
                }
            }
        });
        iyt=view.findViewById(R.id.youtubelogo);
        iyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("http://youtube.com/channel/UCVKzLVOgHcZV-B1ehqa7s9Q");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                intent.setPackage("com.google.android.youtube");
                try{
                    startActivity(intent);
                }catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://youtube.com/channel/UCVKzLVOgHcZV-B1ehqa7s9Q")));
                }
            }
        });



        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Tournament");

        reference.orderByChild("Status").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //progressDialog.dismiss();
                TournamentInfo tournament = dataSnapshot.getValue(TournamentInfo.class);
                //   String matchId=dataSnapshot.getKey().toString();
                listItems.add(tournament);
                Collections.reverse(listItems);
                adapter.notifyDataSetChanged();
                hoardingText.setVisibility(View.GONE);
                titleText.setVisibility(View.VISIBLE);
//                newsText.setVisibility(View.VISIBLE);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                TournamentInfo tournament=dataSnapshot.getValue(TournamentInfo.class);
                if (tournament != null) {
                    for (int i = 0; i < listItems.size(); i++) {
                        if (listItems.get(i).getTournamentName().equals(key)) {
                            listItems.set(i,tournament);
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
                    if (listItems.get(i).getTournamentName().equals(key)) {
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
        });

        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void notific(){

        /*try {
            URL url = new URL("https://www.w3schools.com/w3css/img_lights.jpg");
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        NotificationCompat.BigPictureStyle bigPictureStyle=new NotificationCompat.BigPictureStyle();
        bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.splash)).build();


        NotificationManager notificationManager= (NotificationManager) Objects.requireNonNull(getActivity()).getSystemService(Context.NOTIFICATION_SERVICE);
        Intent resultIntent=new Intent(getActivity(),MainPage.class);
        //resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent=PendingIntent.getActivity(getContext(),(int) Calendar.getInstance().getTimeInMillis(),resultIntent,0);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(getContext())
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
