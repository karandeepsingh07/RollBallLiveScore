package com.karandeepsingh.rollball;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class MatchFragment extends Fragment {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ProgressDialog progressDialog;
    RecyclerView.Adapter adapter;
    FirebaseDatabase database;
    DatabaseReference reference;
    SwipeRefreshLayout swipeRefreshLayout;
    //MyAdapter mAdapter;
    List<Score> listItems;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_match, container, false);
        progressBar=view.findViewById(R.id.progressBar2);
        recyclerView= view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        /*progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");*/
        listItems= new ArrayList<>();

        adapter=new PostAdapter((ArrayList<Score>) listItems,getContext());

        recyclerView.setAdapter(adapter);

        /*for(int i=0;i<5;i++){
            Score score=new Score();
            score.team1Name="cg";
            score.team2Name="pnb";
            listItems.add(score);
            adapter.notifyDataSetChanged();
        }*/

        progressBar.setVisibility(View.VISIBLE);
        loadData();
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.simpleSwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                // cancel the Visual indication of a refresh
                listItems.clear();
                /*shuffleItems();*/
                loadData();
               // swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }


    void loadData(){
        //progressDialog.show();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Match");

        reference.orderByChild("status").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Score score = dataSnapshot.getValue(Score.class);
                //   String matchId= dataSnapshot.getKey().toString();
                listItems.add(score);
                adapter.notifyDataSetChanged();
                if(progressBar.getVisibility()==View.VISIBLE)
                progressBar.setVisibility(View.GONE);
                if(swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                Score newScore=dataSnapshot.getValue(Score.class);
                if (newScore != null) {
                    for (int i = 0; i < listItems.size(); i++) {
                        if (listItems.get(i).getMatchid().equals(key)) {
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
                    if (listItems.get(i).getMatchid().equals(key)) {
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
       // progressBar.setVisibility(View.GONE);
       // progressDialog.hide();
    }


}
