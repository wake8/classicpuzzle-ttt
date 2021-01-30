package com.vunnam.tictactoe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;

public class EnterPlayerNames extends AppCompatActivity {
    EditText xPlayer, oPlayer;
    ImageButton btStart;
    MediaPlayer btSound;
    public static String firstPlayer, secondPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_player_names);
        xPlayer = findViewById(R.id.xPlayer);
        oPlayer = findViewById(R.id.oPlayer);
        btStart = findViewById(R.id.btStart);
        btSound = MediaPlayer.create(this, R.raw.btsound);
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstPlayer = xPlayer.getText().toString();
                secondPlayer = oPlayer.getText().toString();
                if (firstPlayer.equals("") || secondPlayer.equals("")) {
                    //No player names are entered
                    firstPlayer = "PlayerX";
                    secondPlayer = "PlayerO";
                    xPlayer.setText(firstPlayer);
                    oPlayer.setText(secondPlayer);
                }
                //Launch Game Activity
                try {
                    if (btSound.isPlaying()) {
                        btSound.stop();
                        btSound.release();
                        btSound = MediaPlayer.create(getApplicationContext(), R.raw.btsound);
                    }
                    btSound.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(EnterPlayerNames.this, GameActivity.class);
                intent.putExtra("playerMode",2);
                intent.putExtra("xPlayer", firstPlayer);
                intent.putExtra("oPlayer", secondPlayer);
                finish();
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new
                ContextThemeWrapper(this, R.style.AlertDialogCustom));
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> EnterPlayerNames.this.finish())
                .setNegativeButton("No", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }
}