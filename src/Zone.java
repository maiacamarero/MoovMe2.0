import java.util.ArrayList;

public class Zone {

    private ArrayList<Discount> zoneDiscounts;
    private Highscore zoneHighscore;
    private String zoneName;
    private double zoneFarePerMinute;

    public double getDiscount(TypeOfVehicle typeOfVehicle){
        for (Discount zoneDiscount : zoneDiscounts) {
            if (zoneDiscount.correctDiscount(typeOfVehicle)){
                return zoneDiscount.getPointRequirement();
            }
        }
        throw new RuntimeException("hacer exception TypeOfVehicleNotValid");
    }


    public boolean isTopThree(String phoneNumber) {
        return zoneHighscore.isTopThree(phoneNumber);
    }
}
