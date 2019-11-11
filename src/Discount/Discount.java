package Discount;

import Vehicle.TypeOfVehicle.TypeOfVehicle;

public class Discount<T extends TypeOfVehicle> {

    private int pointRequirement;
    private double discountPerPoint, minimumDiscountScore;
    private T typeOfVehicle;

    public Discount(int pointRequirement, double discountPerPoint, T typeOfVehicle, double minimumDiscountScore) {
        this.pointRequirement = pointRequirement;
        this.discountPerPoint = discountPerPoint;
        this.typeOfVehicle = typeOfVehicle;
        this.minimumDiscountScore = minimumDiscountScore;
    }

    public boolean correctDiscount(TypeOfVehicle typeOfVehicle){
        return typeOfVehicle.getClass().getSimpleName().equals(this.typeOfVehicle.getClass().getSimpleName());
    }

    public double getDiscountPerPoint() {
        return discountPerPoint;
    }

    public double getPointRequirement() {
        return pointRequirement;
    }

    public void changeMinimumDiscountScore(double newMinDiscountScore) {
        minimumDiscountScore = newMinDiscountScore;
    }

}
