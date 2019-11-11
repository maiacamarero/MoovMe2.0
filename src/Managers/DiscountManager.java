package Managers;

import Database.ZoneDatabase;
import Discount.Discount;
import Vehicle.TypeOfVehicle.TypeOfVehicle;
import Zone.Zone;

public class DiscountManager {

    public DiscountManager() {

    }

    public void changeMinimumDiscountScore(String zoneName, TypeOfVehicle typeOfVehicle, double newMinimumDiscountScore) {
        Discount zoneDiscount = findDiscount(zoneName, typeOfVehicle);
        zoneDiscount.changeMinimumDiscountScore(newMinimumDiscountScore);
    }

    public void changeDiscountPercentage(String zoneName, TypeOfVehicle typeOfVehicle, double newDiscountPercentage) {
        Discount zoneDiscount = findDiscount(zoneName, typeOfVehicle);
        zoneDiscount.changeDiscountPercentage(newDiscountPercentage);
    }

    private Discount findDiscount(String zoneName, TypeOfVehicle typeOfVehicle) {
        Zone aZone = aZone.aZoneDatabase.findZone(zoneName);
        return aZone.getDiscount(typeOfVehicle);
    }
}
