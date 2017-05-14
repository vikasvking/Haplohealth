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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.widget.Toast.makeText;

public class SignUpActivity extends AppCompatActivity {
    private EditText etFirstName,etLastName,etEmail,etPassword,etConfirmPass,etPhone;
    private Button bSignUp;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myref;
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
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        myref=mFirebaseDatabase.getReference();
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
        // Read from the database
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Object value = dataSnapshot.getValue();
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email=etEmail.getText().toString();
                final String pass=etPassword.getText().toString();
                if(!email.equals("")&&!pass.equals(""))
                {
                    mAuth.createUserWithEmailAndPassword(email,pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        mAuth.signInWithEmailAndPassword(email,pass);
                                        FirebaseUser user=mAuth.getCurrentUser();
                                        String userID=user.getUid();
                                        String fName=etFirstName.getText().toString();
                                        String lNmae=etLastName.getText().toString();
                                        String phone=etPhone.getText().toString();
                                        UserInformation ui=new UserInformation(fName,lNmae,phone);
                                        myref.child(userID).setValue(ui);
                                        mAuth.signOut();
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
