package com.karandeepsingh.rollball;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
                    fragmentTransaction.setCustomAnimations(R.anim.anim_lr,R.anim.anim_rl);
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
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.item1) {
            Intent intent1=new Intent(HomeActivity.this,AboutActivity.class);
            startActivity(intent1);
            return true;
        }
        else if(id==R.id.item2){
            Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://rollballappprivacypolicy.blogspot.com/2019/02/privacy-policy-international-roll-ball.html"));
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id== R.id.homefrg){
            fragment = new HomeFragment();
            manager = getSupportFragmentManager();
            fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentBasic, fragment);
            fragmentTransaction.commit();
        }
        else if (id == R.id.nav_camera) {
            fragment = new AboutrbFragment();
            manager = getSupportFragmentManager();
            fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentBasic, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_gallery) {

            fragment = new HIstoryFragment();
            manager = getSupportFragmentManager();
            fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentBasic, fragment);
            fragmentTransaction.commit();
        }
        else if (id == R.id.rbfi){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.rollball.org/"));
            startActivity(browserIntent);
        }
        else if (id == R.id.applink){
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Roll Ball Live Score");
                String sAux = "\nLet me recommend you this application\n\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id=com.karandeepsingh.rollballlivescore \n\n";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
