package Managers;

import Vehicle.TypeOfVehicle.TypeOfVehicle;
import Zone.Zone;

public class DiscountManager {

    public void changePointRequirement(Zone zone, TypeOfVehicle typeOfVehicle, int newPointRequirement) {
        zone.setPointRequirement(typeOfVehicle, newPointRequirement);
    }

    public void changeDiscountPerPoint(Zone zone, TypeOfVehicle typeOfVehicle, int newDiscountPerPoint) {
        zone.setDiscountPerPoint(typeOfVehicle, newDiscountPerPoint);
    }

}
