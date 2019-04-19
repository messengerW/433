package com.example.f433;

import java.io.Serializable;

public class F2_C2_GameItem implements Serializable {
    public String date;         //  比赛日期
    public String time;         //  具体时间
    public String team1;         //  主队
    public String team2;         //  客队
    public String logo1;         //  主队logo
    public String logo2;         //  客队logo
    public String centertext;        //   VS

    public F2_C2_GameItem() {
    }

    public F2_C2_GameItem(String date, String time, String team1, String team2, String logo1,
                          String logo2, String centertext) {
        this.time = time;
        this.date = date;
        this.team1 = team1;
        this.team2 = team2;
        this.logo1 = logo1;
        this.logo2 = logo2;
        this.centertext = centertext;

    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTeam2() {
        return team2;
    }

    public void setLogo1(String logo1) {
        this.logo1 = logo1;
    }

    public String getLogo1() {
        return logo1;
    }

    public void setLogo2(String logo2) {
        this.logo2 = logo2;
    }

    public String getLogo2() {
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
        return "F2_C2_GameItem{" +
                "date='" + date + '\'' +
                "time='" + time + '\'' +
                "team1='" + team1 + '\'' +
                "team2='" + team2 + '\'' +
                "logo1='" + logo1 + '\'' +
                "logo2='" + logo2 + '\'' +
                "centertext='" + centertext + '\'' +
                '}';
    }
}
