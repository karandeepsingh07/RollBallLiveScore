package com.karandeepsingh.rollball;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(SplashActivity.this);
                    Intent intent=new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(intent);
                finish();
            }
        },1000);
    }
}
