package User;

import Exceptions.UserIsBlockedException;
import Trip.Trip;

public class Client extends User {

    private boolean isClientBlocked;

    public Client(String username, String phoneNumber) {
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

    public void blockUser() {
        isClientBlocked = true;
    }

    public void unblockUser() {
        isClientBlocked = false;
    }
}
