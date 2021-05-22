package com.karandeepsingh.rollball;

/**
 * Created by Karandeep Singh on 20-07-2018.
 */

public class Score {

    String team1Name;
    int team1Score;
    int team1Foul;
    String team2Name;
    int escore1;
    int escore2;
    int team2Score;
    int team2Foul;
    String matchid;
    String imageUrl;
    int status;
    String tTo;
    String half;

    public String getCategory() {
        return category;
    }

    String category;



    int halfsc1;
    int halfsc2;
    int half2sc1;
    int half2sc2;



    public int getStatus() { return status; }

    public String getHalf() { return half; }

    public String getTeam1Name() { return team1Name; }

    public int getTeam1Score() { return team1Score; }

    public int getTeam1Foul() {
        return team1Foul;
    }

    public int getEscore1() { return escore1; }

    public int getEscore2() { return escore2; }

    public String getMatchid(){return matchid;}

    public String getTeam2Name() {
        return team2Name;
    }

    public int getTeam2Score() {
        return team2Score;
    }

    public int getTeam2Foul() {
        return team2Foul;
    }

    public String getImageUrl() { return imageUrl; }

    public int getHalfsc1() { return halfsc1; }

    public int getHalfsc2() { return halfsc2; }

    public int getHalf2sc1() { return half2sc1; }

    public int getHalf2sc2() { return half2sc2; }

}
