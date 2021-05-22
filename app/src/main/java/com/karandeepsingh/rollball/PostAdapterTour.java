package com.karandeepsingh.rollball;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Karandeep Singh on 30-08-2018.
 */

public class PostAdapterTour extends RecyclerView.Adapter<PostAdapterTour.PostViewHolder>{
    private ArrayList<TournamentInfo> items;
    private Context context;

    public PostAdapterTour(ArrayList<TournamentInfo> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.activity_tournament_list,parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {

        TournamentInfo item =items.get(position);
        holder.textView1.setText(item.getTournamentName());
        holder.textView2.setText(item.getStartDate());
        holder.textView3.setText(item.getEndDate());
        holder.textView4.setText(item.getTeamsParticipating());
        holder.textView5.setText(item.getVenue());
        Picasso.get().load(item.getImageUrlbgr()).into(holder.imageView);

        final String tId=item.getTournamentId();
        /*Picasso.get().load(item.getImageUrl()).into(holder.imageView);*/
//        holder.textView5.setText(item.getTeam1Foul());
        //       holder.textView6.setText(item.getTeam2Foul());

        //final String matchId = item.getMatchId();

        //holder.textView7.setText(item.getTeamname());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  DataSnapshot dataSnapshot;
                //String matchId = dataSnapshot.getChildren().iterator().next().getKey().toString();
                // Toast.makeText(context, matchId, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,Main5Activity.class);

                intent.putExtra("Match",tId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{

        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;
        public TextView textView5;
        public TextView textView6;
        public TextView textView7;
        public ImageView imageView;

        public PostViewHolder(View itemView) {
            super(itemView);

            textView1=itemView.findViewById(R.id.textViewTName);
            textView2=itemView.findViewById(R.id.textViewDFrom);
            textView3=itemView.findViewById(R.id.textViewDTo);
            textView4=itemView.findViewById(R.id.textViewTNo);
            textView5=itemView.findViewById(R.id.venue);
            imageView=itemView.findViewById(R.id.imagebGR);
            /*textView6=itemView.findViewById(R.id.textView9);
            textView7=itemView.findViewById(R.id.textView10);
            imageView=itemView.findViewById(R.id.imageView3);*/


        }
    }
}
