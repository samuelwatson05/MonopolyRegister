package com.example.samuel.monopolyregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class gameSet extends AppCompatActivity {

    EditText newGame, joinGame;
    String newGameID, joinGameID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_set);

        joinGame = (EditText) findViewById(R.id.editText);
        newGame = (EditText) findViewById(R.id.editText2);

        Button newGameB = (Button) findViewById(R.id.button6);
        newGameB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newGameID = newGame.getText().toString();
                // put extra into new activity and then push that into firebase either here or there... by that URL

            }
        });

        Button joinGameB = (Button) findViewById(R.id.button7);
        newGameB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                joinGameID = joinGame.getText().toString();
            }
        });

        }

    }
