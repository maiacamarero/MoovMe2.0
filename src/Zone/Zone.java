package Zone;

import Discount.Discount;
import Highscore.Highscore;
import Vehicle.TypeOfVehicle.TypeOfVehicle;

import java.util.ArrayList;

public class Zone {

    private ArrayList<Discount> zoneDiscounts;
    private Highscore zoneHighscore;
    private String zoneName;
    private double zoneFarePerMinute;

    public Zone(ArrayList<Discount> zoneDiscounts, String zoneName, double zoneFarePerMinute) {
        this.zoneDiscounts = zoneDiscounts;
        zoneHighscore = new Highscore();
        this.zoneName = zoneName;
        this.zoneFarePerMinute = zoneFarePerMinute;
    }

    public double getDiscountPerPoint(TypeOfVehicle typeOfVehicle){
        for (Discount zoneDiscount : zoneDiscounts) {
            if (zoneDiscount.correctDiscount(typeOfVehicle)){
                return zoneDiscount.getDiscountPerPoint();
            }
        }
        throw new RuntimeException("hacer exception TypeOfVehicleNotValid");
    }

    public ArrayList<Discount> getZoneDiscounts() {
        return zoneDiscounts;
    }

    public Highscore getZoneHighscore() {
        return zoneHighscore;
    }

    public String getZoneName() {
        return zoneName;
    }

    public boolean isTopThree(String phoneNumber) {
        return zoneHighscore.isTopThree(phoneNumber);
    }

    public double getZoneFarePerMinute() {
        return zoneFarePerMinute;
    }

    public double getDiscountRequirement(TypeOfVehicle typeOfVehicle) {
        for (Discount zoneDiscount : zoneDiscounts) {
            if (zoneDiscount.correctDiscount(typeOfVehicle)){
                return zoneDiscount.getPointRequirement();
            }
        }
        throw new RuntimeException("hacer exception TypeOfVehicleNotValid");
    }
}
