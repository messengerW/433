package com.example.f433;

import java.io.Serializable;

public class Fragment3_GuessItem implements Serializable {
    public String time;         //  比赛时间（包括北京时间+日期+时间）
    public String team1;         //  主队
    public String team2;         //  客队
    public String logo1;         //  主队logo
    public String logo2;         //  客队logo

    public Fragment3_GuessItem(){

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


    @Override
    public String toString() {
        return "Fragment3_GuessItem{" +
                "time='" + time + '\'' +
                "team1='" + team1 + '\'' +
                "team2='" + team2 + '\'' +
                "logo1='" + logo1 + '\'' +
                "logo2='" + logo2 + '\'' +
                '}';
    }
}