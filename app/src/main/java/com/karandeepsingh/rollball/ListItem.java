package com.karandeepsingh.rollball;

/**
 * Created by Karandeep Singh on 25-08-2018.
 */

public class ListItem {
    private String team1,team2,score1,score2,foul1,foul2,teamname;

    public ListItem(String team1,String team2,String score1,String score2,String foul1,String foul2,String teamname){
        this.team1=team1;
        this.team2=team2;
        this.score1=score1;
        this.score2=score2;
        this.foul1=foul1;
        this.foul2=foul2;
        this.teamname=teamname;
    }

    public  String getTeam1(){
        return team1;
    }
    public  String getTeam2(){
        return team2;
    }
    public String getScore1(){
        return score1;
    }
    public String getScore2(){
        return score2;
    }
    public String getFoul1(){
        return foul1;
    }
    public String getFoul2(){
        return foul2;
    }
    public String getTeamname(){return teamname;}
}
