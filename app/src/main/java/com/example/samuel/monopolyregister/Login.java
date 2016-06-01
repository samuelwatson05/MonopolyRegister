package com.example.samuel.monopolyregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class Login extends AppCompatActivity {

    EditText email, password;
    Firebase.AuthResultHandler authResultHandler;
    Firebase firebaseref;
    Button loginClick;
    String userNameString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        firebaseref = new Firebase("https://monopolyregister.firebaseio.com/");
        Firebase.setAndroidContext(this);
        email = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);


        loginClick = (Button) findViewById(R.id.button2);

        loginClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNameString = email.getText().toString();
                passwordString = password.getText().toString();
                firebaseref.authWithPassword(userNameString, passwordString, authResultHandler);
            }
        });

        Button signUp = (Button) findViewById(R.id.button3);
        signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent signupActivity = new Intent(Login.this, SignUp.class);
                startActivity(signupActivity);
            }

        });


        authResultHandler = new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                Firebase.setAndroidContext(getBaseContext());
                Intent webPhoto = new Intent(Login.this, MainActivity.class);
                startActivity(webPhoto);


            }

            public void onAuthenticationError(FirebaseError firebaseError) {
                switch (firebaseError.getCode()) {
                    case FirebaseError.EMAIL_TAKEN:
                        Toast.makeText(Login.this, "Email is already present.", Toast.LENGTH_SHORT).show();
                        break;
                    case FirebaseError.INVALID_EMAIL:
                        Toast.makeText(Login.this, "Check your email", Toast.LENGTH_SHORT).show();
                        break;
                    case FirebaseError.INVALID_PASSWORD:
                        Toast.makeText(Login.this, "Invalid Password.", Toast.LENGTH_SHORT).show();
                        break;
                    case FirebaseError.USER_DOES_NOT_EXIST:
                        Toast.makeText(Login.this, "User does not exist", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }

            }
        };
    }
}
