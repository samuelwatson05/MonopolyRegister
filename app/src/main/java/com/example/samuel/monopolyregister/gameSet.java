package com.example.samuel.monopolyregister;

import android.content.Intent;
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
                Intent i=new Intent(gameSet.this, MainActivity.class);
                i.putExtra("gameID", newGameID);

            }
        });

        Button joinGameB = (Button) findViewById(R.id.button7);
        newGameB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                joinGameID = joinGame.getText().toString();
                Intent i=new Intent(gameSet.this, MainActivity.class);
                i.putExtra("gameID", joinGameID);
            }
        });

        }

    }
