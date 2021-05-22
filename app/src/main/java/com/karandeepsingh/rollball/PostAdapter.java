package com.karandeepsingh.rollball;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Karandeep Singh on 30-08-2018.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{
    private ArrayList<Score> items;
    private Context context;
    private int lastPosition = -1;

    public PostAdapter(ArrayList<Score> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.listine,parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PostViewHolder holder, int position) {

        Score item =items.get(position);
        int t1Score=item.getTeam1Score();
        holder.textView1.setText(item.getTeam1Name());
        holder.textView2.setText(item.getTeam2Name());
        holder.textView3.setText(Integer.toString(t1Score));
        holder.textView4.setText(Integer.toString(item.getTeam2Score()));
        holder.categoryText.setText(item.getCategory());
        /*holder.halfsc1.setText(Integer.toString(item.getHalfsc1()));
        holder.halfsc2.setText(Integer.toString(item.getHalfsc2()));
        holder.half2sc1.setText(Integer.toString(item.getHalf2sc1()));
        holder.half2sc2.setText(Integer.toString(item.getHalf2sc2()));*/
        Picasso.get().load(item.getImageUrl()).into(holder.imageView);
//        int s1=Integer.valueOf(item.getTeam1Score());
//        int s2=Integer.valueOf(item.getTeam2Score());
        if(item.getStatus()==0)
            holder.textView8.setText("Live");
        else if(item.getStatus()==2){
            if((item.getTeam1Score()+item.getEscore1())>(item.getTeam2Score()+item.getEscore2())){
                holder.textView8.setText("Won by "+item.getTeam1Name());
            }
            else if((item.getTeam1Score()+item.getEscore1())==(item.getTeam2Score()+item.getEscore2()))
                holder.textView8.setText("MatchDrawn");

            if((item.getTeam1Score()+item.getEscore1())<(item.getTeam2Score()+item.getEscore2())){
                holder.textView8.setText("Won by "+item.getTeam2Name());
            }
        }
        else if(item.getStatus()==1)
            holder.textView8.setText("Upcoming");

//        holder.textView5.setText(item.getTeam1Foul());
 //       holder.textView6.setText(item.getTeam2Foul());

        final String matchId = item.getMatchid();

        setAnimation(holder.itemView, position);

        //holder.textView7.setText(item.getTeamname());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  DataSnapshot dataSnapshot;
                //String matchId = dataSnapshot.getChildren().iterator().next().getKey().toString();
                // Toast.makeText(context, matchId, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,Main2Activity.class);
                intent.putExtra("Match",matchId);

                Pair<View, String> pair=Pair.create((View)holder.layout,ViewCompat.getTransitionName(holder.layout));
                Pair<View, String> pair1=Pair.create((View)holder.imageView,ViewCompat.getTransitionName(holder.imageView));
                Pair<View, String> pair2=Pair.create((View)holder.textView3,ViewCompat.getTransitionName(holder.textView3));
                Pair<View, String> pair3=Pair.create((View)holder.textView4,ViewCompat.getTransitionName(holder.textView4));
                Pair<View, String> pair4=Pair.create((View)holder.textView1,ViewCompat.getTransitionName(holder.textView1));
                Pair<View, String> pair5=Pair.create((View)holder.textView2,ViewCompat.getTransitionName(holder.textView2));
                Pair<View, String> pair6=Pair.create((View)holder.textDash,ViewCompat.getTransitionName(holder.textDash));
                ActivityOptionsCompat optionsCompat=null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)context,pair1,pair2,pair3,pair4,pair5,pair6);
                }

                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
                    context.startActivity(intent,optionsCompat.toBundle());
                }
                else{
                    context.startActivity(intent);
                }

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
        public TextView textView8;
        public TextView textDash;
        public ImageView imageView;
        public TextView halfsc1;
        public TextView halfsc2;
        public TextView half2sc1;
        public TextView half2sc2;
        public TextView categoryText;
        public ConstraintLayout layout;

        public PostViewHolder(View itemView) {
            super(itemView);

            textView1=itemView.findViewById(R.id.textView);
            textView2=itemView.findViewById(R.id.textView2);
            textView3=itemView.findViewById(R.id.textView3);
            textView4=itemView.findViewById(R.id.textView4);
            textView5=itemView.findViewById(R.id.textView8);
            textView6=itemView.findViewById(R.id.textView9);
            textView7=itemView.findViewById(R.id.textView10);
            textDash=itemView.findViewById(R.id.textView5);
            textView8=itemView.findViewById(R.id.textViewStatus);
            imageView=itemView.findViewById(R.id.imageView3);
            layout=itemView.findViewById(R.id.viewCard);
            /*halfsc1=itemView.findViewById(R.id.halfsc1);
            halfsc2=itemView.findViewById(R.id.halfsc2);
            half2sc1=itemView.findViewById(R.id.half2sc1);
            half2sc2=itemView.findViewById(R.id.half2sc2);*/
            categoryText=itemView.findViewById(R.id.textVIewCategory);


            }
        public void clearAnimation()
        {
            itemView.clearAnimation();
        }
    }


    private void setAnimation(View viewToAnimate, int position)
    {
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull PostViewHolder holder) {
        holder.clearAnimation();
    }
}
