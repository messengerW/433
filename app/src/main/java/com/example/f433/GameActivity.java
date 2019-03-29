package com.example.f433;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //返回按钮
        ImageView imageView = (ImageView) findViewById(R.id.back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //主队积分
        final GameActivity_ScoreBoardView myView = (GameActivity_ScoreBoardView) findViewById(R.id.custom_view);
        //客队积分
        final GameActivity_ScoreBoardView myView2 = (GameActivity_ScoreBoardView) findViewById(R.id.custom_view2);
        //主客队联赛中排名
        final GameActivity_RankBar bar = (GameActivity_RankBar) findViewById(R.id.rank_bar);
        bar.setRanks(1, 1);
        //点击事件
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.setColor(Color.rgb(0, 204, 0));
                myView.setScore(34);
                myView.setWinDrawLose(10, 4, 5);

                myView2.setColor(Color.rgb(102, 204, 255));
                myView2.setScore(31);
                myView2.setWinDrawLose(8, 7, 4);

                bar.setRanks(6, 10);
                bar.setColor(Color.rgb(0, 204, 0), Color.rgb(104, 204, 255));
            }
        });
        myView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.setColor(Color.rgb(0, 204, 0));
                myView.setScore(34);
                myView.setWinDrawLose(10, 4, 5);

                myView2.setColor(Color.rgb(104, 204, 255));
                myView2.setScore(31);
                myView2.setWinDrawLose(8, 7, 4);

                bar.setRanks(6, 10);
                bar.setColor(Color.rgb(0, 204, 0), Color.rgb(104, 204, 255));
            }
        });

    }




}