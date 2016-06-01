package com.example.samuel.monopolyregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

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

        String[] propertyArray = {"Mediterranean", "Baltic", "Oriental", "Vermont", "Connecticut", "St Charles", "States", "St James", "Tennessee", "New York", "Kentucky",
                "Indiana", "Illinois", "Atlantic", "Ventor", "Marvin", "Pacific", "North Carolina", "ParkPlace", "BoardWalk", "Reading Railroad", "Penn", "BBO","ShortLine", "Water Co", "Electric"};
        String[] propertyArrayContent = new String[26];
        Map<String, String> propertyMap = new HashMap<String, String>();
        Firebase usersRef = ref.child("Properties");

        Log.d("ProfileLogin", usersRef.getAuth().getUid().toString());



        if (newGame = true)
        {
            for (int i = 0; i < propertyArray.length; i++)
            {
                propertyArrayContent[i] = "No Owner";
                propertyMap.put(propertyArray[i], propertyArrayContent[i]);
            }
            newGame = false;
        }




        usersRef.setValue(propertyMap);

    }


}
