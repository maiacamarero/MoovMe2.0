package Highscore;

public class ScorePoint {

    private int points, phoneNumber;

    public ScorePoint(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        points = 0;
    }

    int getPoints() {
        return points;
    }

    int getPhoneNumber() {
        return phoneNumber;
    }

    public void addPoints(int newPoints){
        points += newPoints;
    }
}
