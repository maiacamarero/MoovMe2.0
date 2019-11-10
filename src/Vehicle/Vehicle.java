package Vehicle;

import Vehicle.TypeOfVehicle.TypeOfVehicle;
import Zone.Zone;

public class Vehicle {

    private TypeOfVehicle typeOfVehicle;
    private int vehicleId, lotId;
    private double totalMinuteFare;
    private Zone zone;


    public Vehicle(TypeOfVehicle typeOfVehicle, int vehicleId, int lotId, Zone zone) {
        this.typeOfVehicle = typeOfVehicle;
        this.vehicleId = vehicleId;
        this.lotId = lotId;
        this.zone = zone;
        totalMinuteFare = typeOfVehicle.getFare() + zone.getZoneFarePerMinute();
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public int getLotId() {
        return lotId;
    }

    public double getTotalMinuteFare() {
        return totalMinuteFare;
    }

    public Zone getZone() {
        return zone;
    }

    public TypeOfVehicle getTypeOfVehicle() {
        return typeOfVehicle;
    }
}
