package com.example.musicPlayer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        final EditText usernameEd = findViewById(R.id.username);
        String username = usernameEd.getText().toString();
        final EditText passwordEd = findViewById(R.id.password);
        String password = passwordEd.getText().toString();
        EditText confirmpasswordEd = findViewById(R.id.confirmPassword);
        String confirmpassword = confirmpasswordEd.getText().toString();
        if(password != null && !password.isEmpty() && password.equals(confirmpassword) && username!= null && !username.isEmpty()){
            Task<AuthResult> task = auth.createUserWithEmailAndPassword(username,password);
            task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(getApplicationContext(),PlayerActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }

    }
}
