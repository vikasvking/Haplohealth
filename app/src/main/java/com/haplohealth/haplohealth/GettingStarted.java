package com.haplohealth.haplohealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GettingStarted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_getting_started);
        final Button bGetStarted=(Button) findViewById(R.id.bGetStarted);
        bGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent=new Intent(GettingStarted.this,SignInActivity.class);
                startActivity(signInIntent);
            }
        });
    }
}
