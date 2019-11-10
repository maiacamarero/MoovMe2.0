package Highscore;

public class ScorePoint {

    private String phoneNumber;
    private int points;

    public ScorePoint(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        points = 0;
    }

    public int getPoints() {
        return points;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void addPoints(int newPoints){
        points += newPoints;
    }
}
