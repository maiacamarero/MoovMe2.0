public class Discount<T extends TypeOfVehicle> {

    private int pointRequirement;
    private double discountPerPoint;
    private T t;

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
}
