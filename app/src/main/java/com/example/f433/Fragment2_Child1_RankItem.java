package com.example.f433;

import java.io.Serializable;

public class Fragment2_Child1_RankItem implements Serializable {
    public String rank;         //  排名
    public String logo;         //  队徽
    public String name;         //  队名
    public String turn;         //  轮次
    public String num1;         //  胜局
    public String num2;         //  平局
    public String num3;         //  负局
    public String rate;         //  进/失球
    public String points;       //  积分

    public Fragment2_Child1_RankItem() {
    }

    public Fragment2_Child1_RankItem(String rank, String imgPath, String name, String turn, String num1,
                                     String num2, String num3, String rate, String points) {
        this.rank = rank;
        this.logo = imgPath;
        this.name = name;
        this.turn = turn;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.rate = rate;
        this.points = points;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogo() {
        return logo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public String getTurn() {
        return turn;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum3(String num3) {
        this.num3 = num3;
    }

    public String getNum3() {
        return num3;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRate() {
        return rate;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Fragment1_NewsItem{" +
                "rank='" + rank + '\'' +
                "imgPath='" + logo + '\'' +
                "name='" + name + '\'' +
                "turn='" + turn + '\'' +
                "num1='" + num1 + '\'' +
                "num2='" + num2 + '\'' +
                "num3='" + num3 + '\'' +
                "rate='" + rate + '\'' +
                "points='" + points + '\'' +
                '}';
    }
}
