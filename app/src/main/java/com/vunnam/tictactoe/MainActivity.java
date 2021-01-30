package com.vunnam.tictactoe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public ImageButton singlePlayer, twoPlayer;
    MediaPlayer btSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        singlePlayer = findViewById(R.id.AIMode);
        twoPlayer = findViewById(R.id.two_playermode);
        btSound = MediaPlayer.create(this,R.raw.btsound);
        singlePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                intent.putExtra("playerMode",1);
                String firstPlayer = "System";
                String secondPlayer = "You";
                intent.putExtra("xPlayer", firstPlayer);
                intent.putExtra("oPlayer", secondPlayer);
                try {
                    if (btSound.isPlaying()) {
                        btSound.stop();
                        btSound.release();
                        btSound = MediaPlayer.create(getApplicationContext(), R.raw.btsound);
                    } btSound.start();
                } catch(Exception e) { e.printStackTrace(); }
                finish();
                startActivity(intent);
            }
        });
        twoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EnterPlayerNames.class);
                try {
                    if (btSound.isPlaying()) {
                        btSound.stop();
                        btSound.release();
                        btSound = MediaPlayer.create(getApplicationContext(), R.raw.btsound);
                    } btSound.start();
                } catch(Exception e) { e.printStackTrace(); }
                finish();
                startActivity(intent);
            }
        });

    }
}