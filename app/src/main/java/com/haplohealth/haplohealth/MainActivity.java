package com.haplohealth.haplohealth;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static int TIME_SPLASH=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent gettingStartedIntent=new Intent(MainActivity.this,GettingStarted.class);
                startActivity(gettingStartedIntent);
                finish();
            }
        },TIME_SPLASH);
    }
}
