package User;

import Exceptions.UserIsBlockedException;
import Trip.Trip;

public class Client extends User {

    private boolean isClientBlocked;

    public Client(String username, int phoneNumber) {
        super(username, phoneNumber);
        isClientBlocked = false;
    }

    @Override
    public void startTrip(Trip newTrip) throws UserIsBlockedException {
        if(isClientBlocked){
            throw new UserIsBlockedException();
        }
        trip = newTrip;
    }

    public void setBlockedStatus(boolean blockedStatus) {
        isClientBlocked = blockedStatus;
    }

}
