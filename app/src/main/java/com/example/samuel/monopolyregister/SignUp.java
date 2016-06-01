package com.example.samuel.monopolyregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

    public class SignUp extends AppCompatActivity {

        Firebase firebaseref;
        Firebase.AuthResultHandler authResultHandler;
        EditText user, pw, confirmpw, name;
        Button signUpNow;
        String userName, pwString, pwConfirmString, nameString;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sign_up);

            Firebase.setAndroidContext(this);
            firebaseref = new Firebase("https://monopolyregister.firebaseio.com/");
            name = (EditText) findViewById(R.id.name);
            user = (EditText) findViewById(R.id.userName);
            pw = (EditText) findViewById(R.id.password);
            confirmpw = (EditText) findViewById(R.id.confirmPass);
            signUpNow = (Button) findViewById(R.id.button);
            signUpNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userName = user.getText().toString();
                    pwString = pw.getText().toString();
                    pwConfirmString = confirmpw.getText().toString();
                    nameString = name.getText().toString();
                    if (userName.trim().equals("") || pwString.trim().equals("") || pwConfirmString.trim().equals("") || nameString.trim().equals("")) {
                        Toast.makeText(SignUp.this, "You must enter all fields!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!pwString.equals(pwConfirmString)) {
                            Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                        }else{
                            Firebase.setAndroidContext(SignUp.this);
                            firebaseref.authWithPassword(userName, pwString, authResultHandler);
                            firebaseref.createUser(userName, pwString, new Firebase.ValueResultHandler<Map<String, Object>>() {
                                @Override
                                public void onSuccess(Map<String, Object> result) {

                                    Toast.makeText(SignUp.this, "Account Created", Toast.LENGTH_SHORT).show();
                                    Map<String, String> map = new HashMap<String, String>();
                                    Profile profile = new Profile();
                                    profile.setUserName(userName);
                                    profile.setPassword(pwString);
                                    profile.setName(nameString);
                                    map.put("email", profile.getUserName());
                                    map.put("Name", profile.getName());
                                    Map<String, Map<String, String>> profiles = new HashMap<String, Map<String, String>>();
                                    profiles.put(profile.getUserName(), map);
                                    firebaseref.child("Profiles").child(firebaseref.getAuth().getUid().toString()).setValue(profile);
                                    Toast.makeText(SignUp.this, "User is added", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUp.this, Login.class);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onError(FirebaseError firebaseError) {
                                    switch (firebaseError.getCode()) {
                                        case FirebaseError.EMAIL_TAKEN:
                                            Toast.makeText(SignUp.this, "Email is already present.", Toast.LENGTH_SHORT).show();
                                            break;
                                        case FirebaseError.INVALID_EMAIL:
                                            Toast.makeText(SignUp.this, "Check your email", Toast.LENGTH_SHORT).show();
                                            break;
                                        case FirebaseError.INVALID_PASSWORD:
                                            Toast.makeText(SignUp.this, "Invalid Password.", Toast.LENGTH_SHORT).show();
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            });

                        }
                    }
                }
            });
        }
    }
