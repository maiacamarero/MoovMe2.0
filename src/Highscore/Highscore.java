package Highscore;

import java.util.ArrayList;
import java.util.Collections;

public class Highscore {

    private ArrayList<ScorePoint> scorePoints;
    private ArrayList<ScorePoint> topThree;

    public Highscore() {
        topThree = new ArrayList<>();
        scorePoints = new ArrayList<>();
    }

    public void sortScores(){
        Collections.sort(scorePoints, new SortHighscore());
    }

    public ArrayList<ScorePoint> setTopThree(){
        for (int i = 0; i < 3 && scorePoints.get(i) != null; i++) {
            topThree.add(scorePoints.get(i));
        }
        return topThree;
    }

    public void addPoints(int phoneNumber, int points){
        for (ScorePoint scorePoint : scorePoints) {
            if(phoneNumber == scorePoint.getPhoneNumber()){
                scorePoint.addPoints(points);
            }
        }
    }

    public boolean isTopThree(int phoneNumber) {
        for (ScorePoint scorePoint : topThree) {
            if (scorePoint.getPhoneNumber() == phoneNumber){
                topThree.remove(scorePoint);
                return true;
            }
        }
        return false;
    }
}
