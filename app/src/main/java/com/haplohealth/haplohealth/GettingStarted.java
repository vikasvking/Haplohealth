package com.haplohealth.haplohealth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GettingStarted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_getting_started);
    }
}
