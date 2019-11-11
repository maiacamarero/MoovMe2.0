package Lot;

import Vehicle.Vehicle;

import java.util.ArrayList;

class VehicleLot {

    private int lotId;
    private ArrayList<Vehicle> vehicles;

    VehicleLot(int lotId, ArrayList<Vehicle> vehicles) {
        this.lotId = lotId;
        this.vehicles = vehicles;
    }

    ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

}