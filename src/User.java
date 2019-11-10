import State.DeliveredOnTime;

public abstract class User {

    private String username, phoneNumber;
    private Trip trip;
    private int usablePoints;
    private boolean isBlocked;

    public User(String username, String phoneNumber) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        isBlocked = false;
    }

    public void endTrip(){

    }

    public double applyPointsDiscount(int amountOfPoints){
        return amountOfPoints * trip.getZone().getDiscount(trip.getVehicle().getTypeOfVehicle());
    }

    public double payTrip(int amountOfPoints){
        //chequea descuentos automaticos
        double amountToPay = trip.getPriceOfTrip();
        amountToPay *= applyTop3Discount();
        amountToPay *= applyDeliveryOnTime();
        amountToPay -= applyPointsDiscount(amountOfPoints);
        return amountToPay;
    }

    public double applyDeliveryOnTime() {
        if (trip.getState().equals(new DeliveredOnTime())){
            return 0.8;
        }else{
            return 1;
        }
    }

    public double applyTop3Discount(){
        if (trip.getZone().isTopThree(phoneNumber)){
            return 0.5;
        }else{
            return 1;
        }
    }

    public abstract void startTrip();

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
