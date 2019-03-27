package com.example.f433;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final GameActivity_ScoreBoardView myView=(GameActivity_ScoreBoardView)findViewById(R.id.custom_view);
        myView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                myView.setColor(Color.rgb(102,204,255));
                myView.setScore(34);
                myView.setWinDrawLose(10,4,5);
            }
    });
    }

}
