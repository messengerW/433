package com.example.f433;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //主队积分
        final GameActivity_ScoreBoardView myView = (GameActivity_ScoreBoardView) findViewById(R.id.custom_view);
        //客队积分
        final GameActivity_ScoreBoardView myView2 = (GameActivity_ScoreBoardView) findViewById(R.id.custom_view2);
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.setColor(Color.rgb(102, 204, 255)); //waterBlue
                myView.setScore(34);
                myView.setWinDrawLose(10, 4, 5);

                myView2.setColor(Color.rgb(0, 255, 0));
                myView2.setScore(31);
                myView2.setWinDrawLose(8, 7, 4);
            }
        });
        myView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.setColor(Color.rgb(102, 204, 255)); //waterBlue
                myView.setScore(34);
                myView.setWinDrawLose(10, 4, 5);

                myView2.setColor(Color.rgb(0, 255, 0));
                myView2.setScore(31);
                myView2.setWinDrawLose(8, 7, 4);
            }
        });

        //主客队联赛中排名
        final GameActivity_RankBar bar = (GameActivity_RankBar) findViewById(R.id.rank_bar);
        bar.setRanks(1, 1);
        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.setRanks(6, 10);
            }
        });
    }

}