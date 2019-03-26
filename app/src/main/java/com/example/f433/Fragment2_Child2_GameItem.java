package com.example.f433;

import java.io.Serializable;

public class Fragment2_Child2_GameItem implements Serializable {
    public String date;         //  比赛日期
    public String team1;         //  主队
    public String team2;         //  客队
    public String logo1;         //  主队logo
    public String logo2;         //  客队logo
    public String centertext;        //   VS

    public Fragment2_Child2_GameItem() {
    }

    public Fragment2_Child2_GameItem(String date, String team1, String team2, String logo1,
                                     String logo2,String centertext) {
        this.date = date;
        this.team1 = team1;
        this.team2 = team2;
        this.logo1 = logo1;
        this.logo2 = logo2;
        this.centertext = centertext;

    }

    public void setdate(String date) {
        this.date = date;
    }

    public String getdate() {
        return date;
    }

    public void setteam1(String team1) {
        this.team1 = team1;
    }

    public String getteam1() {
        return team1;
    }

    public void setteam2(String team2) {
        this.team2 = team2;
    }

    public String getteam2() {
        return team2;
    }

    public void setlogo1(String logo1) {
        this.logo1 = logo1;
    }

    public String getlogo1() {
        return logo1;
    }

    public void setlogo2(String logo2) {
        this.logo2 = logo2;
    }

    public String getlogo2() {
        return logo2;
    }

    public void setCentertext(String centertext) {
        this.centertext = centertext;
    }

    public String getCentertext() {
        return centertext;
    }

    @Override
    public String toString() {
        return "Fragment1_NewsItem{" +
                "date='" + date + '\'' +
                "team1='" + team1 + '\'' +
                "team2='" + team2 + '\'' +
                "logo1='" + logo1 + '\'' +
                "logo2='" + logo2 + '\'' +
                "centertext='" + centertext + '\'' +
                '}';
    }
}
