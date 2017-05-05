package com.haplohealth.haplohealth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.widget.Toast.makeText;

public class SignUpActivity extends AppCompatActivity {
    private EditText etFirstName,etLastName,etEmail,etPassword,etConfirmPass,etPhone;
    private Button bSignUp;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "SignUpActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);
        etFirstName=(EditText) findViewById(R.id.etFirstName);
        etLastName=(EditText) findViewById(R.id.etLastName);
        etEmail=(EditText) findViewById(R.id.etEmail);
        etPassword=(EditText) findViewById(R.id.etPassword);
        etConfirmPass=(EditText) findViewById(R.id.etConfirmPassword);
        etPhone=(EditText) findViewById(R.id.etPhone);
        bSignUp=(Button) findViewById(R.id.bSignUp);
        // authentication
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=etEmail.getText().toString();
                String pass=etPassword.getText().toString();
                if(!email.equals("")&&!pass.equals(""))
                {
                    mAuth.createUserWithEmailAndPassword(email,pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        toastMsg("Sign up complete");
                                        startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
                                    }
                                    else{
                                        toastMsg("error");
                                    }
                                }
                            });



                }
            }
        });


    }

    private void toastMsg(String s) {
        Toast toast=makeText(this,s,Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
