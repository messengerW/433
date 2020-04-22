package com.example.f433.Activities.Analysis;

import java.io.Serializable;
import android.graphics.drawable.Drawable;

public class AnalysisBean implements Serializable {

    public float aerialDuelScc;
    public int aerialsWon;
    public int assists;
    public int bigChanceCreated;
    public int clearances;
    public int dribblesWon;
    public int errorsSum;
    public int finalThirdPass;
    public int finalThirdPassAcc;
    public int fouled;
    public int fouls;
    public int goals;
    public int goalsLost;
    public int interceptions;
    public int keyPasses;
    public int offsideWon;
    public int offsides;
    public float passSucc;
    public int passes;
    public float possession;
    public float rate;
    public int redCards;
    public int shots;
    public int shotsOT;
    public int tacklesSuccful;
    public int teamId;
    public String teamName;
    public int yelCards;
    public int isHome;
    public String date;
    public String opponent;


    public AnalysisBean() {
    }

    public AnalysisBean(
            float aerialDuelScc,
            int aerialsWon,
            int assists,
            int bigChanceCreated,
            int clearances,
            int dribblesWon,
            int errorsSum,
            int finalThirdPass,
            int finalThirdPassAcc,
            int fouled,
            int fouls,
            int goals,
            int goalsLost,
            int interceptions,
            int keyPasses,
            int offsideWon,
            int offsides,
            float passSucc,
            int passes,
            float possession,
            float rate,
            int redCards,
            int shots,
            int shotsOT,
            int tacklesSuccful,
            int teamId,
            String teamName,
            int yelCards,
            int isHome,
            String date,
            String opponent){
        this.aerialDuelScc = aerialDuelScc;
        this.aerialsWon = aerialsWon;
        this.assists = assists;
        this.bigChanceCreated = bigChanceCreated;
        this.clearances = clearances;
        this.dribblesWon = dribblesWon;
        this.errorsSum = errorsSum;
        this.finalThirdPass = finalThirdPass;
        this.finalThirdPassAcc = finalThirdPassAcc;
        this.fouled = fouled;
        this.fouls = fouls;
        this.goals = goals;
        this.goalsLost = goalsLost;
        this.interceptions = interceptions;
        this.keyPasses = keyPasses;
        this.offsideWon = offsideWon;
        this.offsides = offsides;
        this.passSucc = passSucc;
        this.passes = passes;
        this.possession = possession;
        this.rate = rate;
        this.redCards = redCards;
        this.shots = shots;
        this.shotsOT = shotsOT;
        this.tacklesSuccful = tacklesSuccful;
        this.teamId = teamId;
        this.teamName = teamName;
        this.yelCards = yelCards;
        this.isHome = isHome;
        this.date = date;
        this.opponent = opponent;
        
    }

    public void setAerialDuelScc(float aerialDuelScc) {this.aerialDuelScc = aerialDuelScc;}
    public float getAerialDuelScc() {return aerialDuelScc;}

    public void setAerialsWon(int aerialsWon){this.aerialsWon = aerialsWon;}
    public int getAerialsWon(){return aerialsWon;}

    public void setAssists(int assists){this.assists = assists;}
    public int getAssists(){return assists;}

    public void setBigChanceCreated(int bigChanceCreated){this.bigChanceCreated = bigChanceCreated;}
    public int getBigChanceCreated(){return bigChanceCreated;}

    public void setClearances(int clearances){this.clearances = clearances;}
    public int getClearances(){return clearances;}

    public void setDribblesWon(int dribblesWon){this.dribblesWon = dribblesWon;}
    public int getDribblesWon(){return dribblesWon;}

    public void setErrorsSum(int errorsSum){this.errorsSum = errorsSum;}
    public int getErrorsSum(){return errorsSum;}

    public void setFinalThirdPass(int finalThirdPass){this.finalThirdPass = finalThirdPass;}
    public int getFinalThirdPass(){return finalThirdPass;}

    public void setFinalThirdPassAcc(int finalThirdPassAcc){this.finalThirdPassAcc = finalThirdPassAcc;}
    public int getFinalThirdPassAcc(){return finalThirdPassAcc;}

    public void setFouled(int fouled){this.fouled = fouled;}
    public int getFouled(){return fouled;}

    public void setFouls(int fouls){this.fouls = fouls;}
    public int getFouls(){return fouls;}

    public void setGoals(int goals){this.goals = goals;}
    public int getGoals(){return goals;}

    public void setGoalsLost(int goalsLost){this.goalsLost = goalsLost;}
    public int getGoalsLost(){return goalsLost;}

    public void setInterceptions(int interceptions){this.interceptions = interceptions;}
    public int getInterceptions(){return interceptions;}

    public void setKeyPasses(int keyPasses){this.keyPasses = keyPasses;}
    public int getKeyPasses(){return keyPasses;}

    public void setOffsideWon(int offsideWon){this.offsideWon = offsideWon;}
    public int getOffsideWon(){return offsideWon;}

    public void setOffsides(int offsides){this.offsides = offsides;}
    public int getOffsides(){return offsides;}


//    @Override
//    public String toString() {
//        return "F2_C2_GameBean{" +
//                "date='" + date + '\'' +
//                "time='" + time + '\'' +
//                "team1='" + team1 + '\'' +
//                "team2='" + team2 + '\'' +
//                "logo1='" + logo1 + '\'' +
//                "logo2='" + logo2 + '\'' +
//                "centertext='" + centertext + '\'' +
//                '}';
//    }
}

