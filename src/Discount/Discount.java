package Discount;

import Vehicle.TypeOfVehicle.TypeOfVehicle;

public class Discount<T extends TypeOfVehicle> {

    private int pointRequirement;
    private double discountPerPoint;
    private T typeOfVehicle;

    public Discount(int pointRequirement, double discountPerPoint, T typeOfVehicle) {
        this.pointRequirement = pointRequirement;
        this.discountPerPoint = discountPerPoint;
        this.typeOfVehicle = typeOfVehicle;
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

}
