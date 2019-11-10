package Vehicle.TypeOfVehicle;

public class Scooter extends TypeOfVehicle {

    static final double SCOOTER_FARE = 1;
    static final int SCOOTER_SCORE = 20;
    static final int SCOOTER_FEE= 2000;

    @Override
    public double getFare() {
        return SCOOTER_FARE;
    }

    @Override
    public int getScore() {
        return SCOOTER_SCORE;
    }

    @Override
    public int getFee() {
        return SCOOTER_FEE;
    }
}
