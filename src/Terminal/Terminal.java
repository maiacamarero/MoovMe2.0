package Terminal;

import Vehicle.Vehicle;
import Zone.Zone;

import java.util.ArrayList;
import java.util.HashMap;

public class Terminal {

    private Zone terminalZone;
    private HashMap<Integer, Vehicle> vehiclesInTerminal;
    private ArrayList<Vehicle> vehicles;
    private int terminalId;

    public Terminal(Zone terminalZone, int terminalId){
        this.terminalZone = terminalZone;
        this.terminalId = terminalId;
        vehiclesInTerminal = new HashMap<>();
        vehicles = new ArrayList<Vehicle>();
    }

    public void addVehiclesToTerminal(ArrayList<Vehicle> vehicles){
        for (Vehicle aVehicle : vehicles) {
            addVehicleToTerminal(aVehicle.getVehicleId(), aVehicle);
        }
    }

    public void addVehicleToTerminal(int vehicleId, Vehicle aVehicle) {
        vehiclesInTerminal.put(vehicleId, aVehicle);
        for (Vehicle vehicle : vehicles) {
            if(vehicle.getVehicleId() == vehicleId){
                return;
            }
        }
        vehicles.add(aVehicle);
    }

    public int getTerminalId() {
        return terminalId;
    }

    public boolean checkForVehicleInTerminal(int vehicleId) {
        return vehiclesInTerminal.containsKey(vehicleId);
    }

    public Vehicle getVehicle(int vehicleId) {
        Vehicle aVehicle = vehiclesInTerminal.get(vehicleId);
        vehiclesInTerminal.remove(vehicleId);
        for (Vehicle vehicle : vehicles) {
            if(vehicle.getVehicleId() == vehicleId){
                return vehicle;
            }
        }
        return aVehicle;
    }

    public Zone getTerminalZone() {
        return terminalZone;
    }
}