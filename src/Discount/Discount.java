package Discount;

import Vehicle.TypeOfVehicle.TypeOfVehicle;

public class Discount<T extends TypeOfVehicle> {

    private int pointRequirement;
    private double discountPerPoint;
    private T t;
    private double minimumDiscountScore, discountPercentage;

    public boolean correctDiscount(TypeOfVehicle typeOfVehicle){
        if (typeOfVehicle.getClass().getSimpleName().equals(t.getClass().getSimpleName())){
            return true;
        }else{
            return false;
        }
    }

    public double getPointRequirement() {
        return pointRequirement;
    }

    public void changeMinimumDiscountScore(double newMinDiscountScore) {
        minimumDiscountScore = newMinDiscountScore;
    }

    public void changeDiscountPercentage(double newDiscountPercentage) {
        discountPercentage = newDiscountPercentage;
    }

}
