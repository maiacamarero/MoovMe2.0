package Lot;

import Vehicle.TypeOfVehicle.TypeOfVehicle;
import Vehicle.Vehicle;

import java.util.ArrayList;

public class VehicleLot<T extends TypeOfVehicle> {

    private int lotId;
    private ArrayList<Vehicle> vehicles;

    public VehicleLot(int lotId, ArrayList<Vehicle> vehicles) {
        this.lotId = lotId;
        this.vehicles = vehicles;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }



}