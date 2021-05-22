package com.karandeepsingh.rollball;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainPage extends AppCompatActivity {

    private TextView mTextMessage;
    private Button b1;
    Fragment fragment=null;
    FragmentManager manager;
    FragmentTransaction fragmentTransaction;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    //
                    fragment = new HomeFragment();
                    manager = getSupportFragmentManager();
                    fragmentTransaction = manager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.anim_lr,R.anim.anim_rl);
                    fragmentTransaction.replace(R.id.fragmentBasic, fragment);
                    fragmentTransaction.commit();
                    /**/
                    //fragmentTransaction.addToBackStack(null);
                    //fragmentTransaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                   // mTextMessage.setText(R.string.title_dashboard);
                    fragment = new MatchFragment();
                    manager = getSupportFragmentManager();
                    fragmentTransaction = manager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.anim_lr,R.anim.anim_rl);
                    fragmentTransaction.replace(R.id.fragmentBasic, fragment);
                    fragmentTransaction.commit();
                    return true;
                /*case R.id.navigation_WebsiteRB:
                    //mTextMessage.setText(R.string.title_WebsiteRB);
                    fragment = new WebsiteRB();
                    manager = getSupportFragmentManager();
                    fragmentTransaction = manager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentBasic, fragment);
                    fragmentTransaction.commit();
                    return true;*/

                case R.id.navigation_RegistrationRB:
                   //
                    // mTextMessage.setText(R.string.title_RegistrationRB);
                    fragment=new RegistrationRB();
                    manager=getSupportFragmentManager();
                    fragmentTransaction=manager.beginTransaction();
                   // fragmentTransaction.setCustomAnimations(R.anim.anim_lr,R.anim.anim_rl);
                    fragmentTransaction.replace(R.id.fragmentBasic,fragment);
                    fragmentTransaction.commit();
                    return true;
            }
            /*FragmentManager manager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=manager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentBasic,fragment);
            fragmentTransaction.commit();*/
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                notific();
//            }
//        });
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
                Intent intent1=new Intent(MainPage.this,AboutActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    /*private void notific(){

     *//*try {
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
    }*/

}
