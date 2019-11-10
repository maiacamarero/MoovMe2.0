package User;

import Trip.Trip;

public class Administrator extends User {

    public Administrator(String username, String phoneNumber) {
        super(username, phoneNumber);
    }

    @Override
    public void startTrip(Trip newTrip) {
        trip = newTrip;
    }
}
