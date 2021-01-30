package com.vunnam.tictactoe;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;

import java.util.HashMap;

public class GameActivity extends AppCompatActivity {
    public static int playerMode = 1;
    public static int humanTurn = 0;
    public ImageButton box00, box01, box02,
            box10, box11, box12,
            box20, box21, box22;
    public static String xPlayer = "";
    public static String oPlayer = "";
    public static String[] players;
    public static String playerBody;
    public MediaPlayer xyClick;
    public TextView playerName;
    public static String[] board = {"0","1","2","3","4","5","6","7","8","9"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        xPlayer = getIntent().getExtras().getString("xPlayer");
        oPlayer = getIntent().getExtras().getString("oPlayer");
        playerName = findViewById(R.id.playerText);
        xyClick = MediaPlayer.create(this,R.raw.xyclick);
        players = new String[]{xPlayer, oPlayer};
        playerBody = "Player Turn: "+players[humanTurn];

        playerMode = getIntent().getIntExtra("playerMode", 1);
        if (playerMode == 1) {
            callAIMode();
        } else {
            callHumanMode();
        }

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new
                ContextThemeWrapper(this, R.style.AlertDialogCustom));
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> GameActivity.this.finish())
                .setNegativeButton("No", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }

    //AI Mode
    public void callAIMode() {
    }

    //Human Mode Starts in this Method
    public void callHumanMode() {
        playerName.setText(playerBody);
        //Create a Method that checks every button click turn wise
        box00 = findViewById(R.id.box00);
        box01 = findViewById(R.id.box01);
        box02 = findViewById(R.id.box02);
        box10 = findViewById(R.id.box10);
        box11 = findViewById(R.id.box11);
        box12 = findViewById(R.id.box12);
        box20 = findViewById(R.id.box20);
        box21 = findViewById(R.id.box21);
        box22 = findViewById(R.id.box22);
        //All Buttons Click Listeners must call single method to check winner and turn and change image
        box00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueChanger(box00);
            }
        });
        box01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueChanger(box01);
            }
        });
        box02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueChanger(box02);
            }
        });
        box10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueChanger(box10);
            }
        });
        box11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueChanger(box11);
            }
        });
        box12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueChanger(box12);
            }
        });
        box20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueChanger(box20);
            }
        });
        box21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueChanger(box21);
            }
        });
        box22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueChanger(box22);
            }
        });


    }




    private void makeClickSound(){
        try {
            if (xyClick.isPlaying()) {
                xyClick.stop();
                xyClick.release();
                xyClick = MediaPlayer.create(getApplicationContext(), R.raw.btsound);
            }
            xyClick.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void valueChanger(ImageButton currentBox){
        /*
        * box00 = 0
        * box01 = 1
        * box02 = 2
        * box10 = 3
        * box11 = 4
        * box12 = 5
        * box20 = 6
        * box21 = 7
        * box22 = 8*/
        makeClickSound();
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(box00.getId(),0);
        map.put(box01.getId(),1);
        map.put(box02.getId(),2);
        map.put(box10.getId(),3);
        map.put(box11.getId(),4);
        map.put(box12.getId(),5);
        map.put(box20.getId(),6);
        map.put(box21.getId(),7);
        map.put(box22.getId(),8);
        int cKey = currentBox.getId();
        CheckWinner checkWinner = new CheckWinner();
        /*
        * call checkWin()
        * change the button image based on turn
        * change playerneme of text view based on turn
        * update board value with box id bassed on turn*/
        String status = checkWinner.checkWin(board);
        if(status.equals("X")){
            //ToDo Handle X winner
        }else if(status.equals("O")){
            //ToDo Handle O winner
        }else if(status.equals("D")){
            //ToDo handle Draw situation
        }else{
            // return value is Nu
            //ToDo game continues
            if(humanTurn == 0){
                //Player X turn
                //ToDo update button image and board value and turn and Player text
                if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
                {
                    currentBox.setImageResource(getResources().getIdentifier("x", "drawable", getPackageName()));
                }
                else
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        currentBox.setImageDrawable(getDrawable(getResources().getIdentifier("x", "drawable", getPackageName())));
                    }
                }
                int index = map.get(currentBox.getId());
                board[index] = "X";
                //ToDo Check for winners again
                CheckWinner checkWinner1 = new CheckWinner();
                String status1 = checkWinner1.checkWin(board);
                if(status1.equals("X")){
                    //ToDo handle x winner
                }else if(status1.equals("Y")){
                    //toDo handle y winner
                }else if(status1.equals("D")){
                    //toDo handle draw situation
                }else{
                    //Continue
                    humanTurn = 1;
                    playerName.setText(playerBody);
                }
            }else{
                //Player O turn
                //ToDo update button image and board value and turn and Player text
                if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
                {
                    currentBox.setImageResource(getResources().getIdentifier("o", "drawable", getPackageName()));
                }
                else
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        currentBox.setImageDrawable(getDrawable(getResources().getIdentifier("o", "drawable", getPackageName())));
                    }
                }
                int index = map.get(currentBox.getId());
                board[index] = "O";
                //ToDo Check for winners again
                CheckWinner checkWinner1 = new CheckWinner();
                String status1 = checkWinner1.checkWin(board);
                if(status1.equals("X")){
                    //ToDo handle x winner
                }else if(status1.equals("Y")){
                    //toDo handle y winner
                }else if(status1.equals("D")){
                    //toDo handle draw situation
                }else{
                    //Continue
                    humanTurn = 0;
                    playerName.setText(playerBody);
                }
            }

        }
    }
}