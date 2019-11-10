package Vehicle.TypeOfVehicle;


public class Bycicle extends TypeOfVehicle {

    static final double BYCICLE_FARE = 0.8;
    static final int BYCICLE_SCORE = 10 ;
    static final int BYCICLE_FEE = 1000;

    @Override
    public double getFare() {
        return BYCICLE_FARE;
    }

    @Override
    public int getScore() {
        return BYCICLE_SCORE;
    }

    @Override
    public int getFee() {
        return BYCICLE_FEE;
    }
}
