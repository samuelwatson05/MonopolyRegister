package com.example.samuel.monopolyregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //Intent intent = getIntent();
    //String gameID = intent.getStringExtra("gameID");

    ListView propertyListView;
    String[] propertyArray = {"Mediterranean", "Baltic", "Oriental", "Vermont", "Connecticut", "St Charles", "States", "St James", "Tennessee", "New York", "Kentucky",
            "Indiana", "Illinois", "Atlantic", "Ventor", "Marvin", "Pacific", "North Carolina", "ParkPlace", "BoardWalk", "Reading Railroad", "Penn", "BBO", "ShortLine", "Water Co", "Electric"};
    Firebase propertyRef = new Firebase("https://monopolyregister.firebaseio.com/Properties");

    ArrayList<String> propertyList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Boolean newGame = true;
        String Mediterranean, Baltic, Oriental, Vermont, Connecticut, StCharles, States, Virginia = "no owner";
        String StJames, Tennessee, NewYork, Kentucky, Indiana, Illinois, Atlantic, Ventor, Marvin, Pacific, NorthCarolina = null;
        String ParkPlace, BoardWalk, readingRR, pennRR, BBORR, shortLineRR, water, electric = null;
        Firebase ref = new Firebase("https://monopolyregister.firebaseio.com/");
        Profile profref = new Profile();


        String[] propertyArrayContent = new String[26];
        Map<String, String> propertyMap = new HashMap<String, String>();
        
        Firebase usersRef = ref.child("Properties");

        Log.d("ProfileLogin", usersRef.getAuth().getUid().toString());

        propertyList = new ArrayList<String>();



        propertyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("changeData", dataSnapshot.getValue().toString());
                propertyList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Log.d("PropertyString", postSnapshot.toString() + dataSnapshot.toString());

                    propertyList.add(postSnapshot.getKey() + " " +  postSnapshot.getValue().toString());


                }

                propertyListView = (ListView) findViewById(R.id.listView);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, propertyList);

                propertyListView.setAdapter(arrayAdapter);
            }



            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        propertyRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Log.d("PropertyString", postSnapshot.toString() + dataSnapshot.toString());

                    propertyList.add(postSnapshot.getKey() + " " +  postSnapshot.getValue().toString());


                }
                propertyListView = (ListView) findViewById(R.id.listView);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, propertyList);

                propertyListView.setAdapter(arrayAdapter);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        if (newGame = true) {
            for (int i = 0; i < propertyArray.length; i++) {
                propertyArrayContent[i] = "No Owner";
                propertyMap.put(propertyArray[i], propertyArrayContent[i]);
            }
            newGame = false;
        }

        usersRef.setValue(propertyMap);



    }
}

