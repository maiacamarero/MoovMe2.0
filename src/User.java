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

    public double applyPointDiscount(int amountOfPoints){
        return amountOfPoints * trip.getZone().getDiscount(trip.getVehicle().getTypeOfVehicle());
    }

    public double payTrip(int amountOfPoints){
        //chequea descuentos automaticos
        double amountToPay = trip.getPriceOfTrip();
        amountToPay *= applyTop3Discount();
        amountToPay *= applyDeliveryOnTime();
        amountToPay -= applyPointDiscount(amountOfPoints);
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

    public int canApplyPointDiscount(int amountOfPoints) {
        if (trip.getZone().getDiscount(trip.getVehicle().getTypeOfVehicle()) > usablePoints) {
            return 1; // insuficient points
        }else if(amountOfPoints < trip.getZone().getDiscount(trip.getVehicle().getTypeOfVehicle())){
            return 2; // minimum not reached
        }else if(amountOfPoints > usablePoints){
            return 3; // you dont have that many points
        }else{
            return 4; // to piola atr
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double payTrip() {
        return payTrip(0);
    }
}
