package com.example.musicPlayer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView loginTv = findViewById(R.id.loginTv);
        loginTv.setTextColor(Color.RED);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if(user!=null){
            Intent intent = new Intent(getApplicationContext(),PlayerActivity.class);
            startActivity(intent);
        }
        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameEd = findViewById(R.id.username);
                String username = usernameEd.getText().toString();
                EditText passwordEd = findViewById(R.id.password);
                String password = passwordEd.getText().toString();
                Task<AuthResult> task = auth.signInWithEmailAndPassword(username,password);
                task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(),PlayerActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                task.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        loginTv.setText(e.getLocalizedMessage());
                    }
                });
            }
        });

    }

}
