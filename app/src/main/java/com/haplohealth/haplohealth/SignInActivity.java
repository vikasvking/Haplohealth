package com.haplohealth.haplohealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {
    private EditText etEmail,etPassword;
    private TextView tvForgetPassword,tvSignUp,tvClinicLogIn;
    private Button bLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_in);
        etEmail=(EditText) findViewById(R.id.etEmail);
        etPassword=(EditText) findViewById(R.id.etPassword);
        tvForgetPassword=(TextView) findViewById(R.id.tvForgotPassword);
        tvClinicLogIn=(TextView) findViewById(R.id.tvClinicLogIn);
        tvSignUp=(TextView) findViewById(R.id.tvSignUp);
        bLogIn=(Button) findViewById(R.id.bLogIn);


        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent=new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });

    }
}
