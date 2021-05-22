package com.karandeepsingh.rollball;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PostAdapterNews extends ArrayAdapter {

    private List newslist =new ArrayList<PostAdapterNews>();

    public PostAdapterNews(Context context, int resource) {
        super(context, resource);
    }

    public void addItem(NewsClass object){
        newslist.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return newslist.size();
    }

    @Override
    public Object getItem(int position) {
        return newslist.get(position);
    }

    private static class StudentUI{
        ImageView img;
        TextView txt_name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater;
        final StudentUI holder;
        View row=convertView;
        if(row==null){
            //Toast.makeText(getContext(), "Context View is Null", Toast.LENGTH_SHORT).show();
            inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.news_list,parent,false);
            holder=new StudentUI();
            holder.img=row.findViewById(R.id.student_image);
            holder.txt_name=row.findViewById(R.id.student_name);
            row.setTag(holder);
        }
        else
            holder= (StudentUI) row.getTag();

        NewsClass sc= (NewsClass) getItem(position);
        final String tId=sc.getNewsId();
        Picasso.get().load(sc.getImgUrl()).into(holder.img);
        holder.txt_name.setText(sc.getHeading());

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Main3Activity.class);
                intent.putExtra("News",tId);

                Pair<View, String> pair1=Pair.create((View)holder.img,ViewCompat.getTransitionName(holder.img));
                Pair<View, String> pair2=Pair.create((View)holder.txt_name,ViewCompat.getTransitionName(holder.txt_name));
                ActivityOptionsCompat optionsCompat=null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)getContext(),pair1,pair2);
                }

                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
                    getContext().startActivity(intent,optionsCompat.toBundle());
                }
                else{
                    getContext().startActivity(intent);
                }
            }
        });
        return row;
    }
}
