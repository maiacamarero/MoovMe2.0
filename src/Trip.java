import State.State;

import java.time.Duration;
import java.time.LocalDateTime;

public class Trip {

    private User user;
    private Vehicle vehicle;
    private LocalDateTime startTime, endTime;
    private double durationInMins;
    private Zone zone;
    private double priceOfTrip;
    private State state;

    public Trip(User user, Vehicle vehicle, Zone zone) {
        this.user = user;
        this.vehicle = vehicle;
        this.zone = zone;
        startTime = LocalDateTime.now();
        priceOfTrip = 0;
    }

    public void setEndTime(){
        endTime = LocalDateTime.now();
        durationInMins = ((Duration.between(startTime, endTime)).getSeconds())/60;
    }

    public Zone getZone() {
        return zone;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getPriceOfTrip() {
        return priceOfTrip;
    }

    public boolean discountIsAvailable(int amountOfPoints) {
        
    }

    public State getState() {
        return state;
    }
}
