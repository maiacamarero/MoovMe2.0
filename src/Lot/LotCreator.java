package Lot;

import IdGenerator.IdGenerator;
import Terminal.Terminal;
import Vehicle.TypeOfVehicle.TypeOfVehicle;
import Vehicle.Vehicle;

import java.util.ArrayList;

public class LotCreator {

    public void sendVehicleLotToTerminal(TypeOfVehicle typeOfVehicleForLot, int numberOfVehicles, Terminal terminalToAddLot, int newLotId) {
        ArrayList<Vehicle> vehicles = new ArrayList<>(numberOfVehicles);
        for (int i = 0; i < numberOfVehicles; i++) {
            vehicles.add(new Vehicle(typeOfVehicleForLot, IdGenerator.getNewVehicleId(), newLotId, terminalToAddLot.getTerminalZone()));
        }
        VehicleLot newVehicleLot = new VehicleLot(newLotId, vehicles);
        terminalToAddLot.addVehiclesToTerminal(newVehicleLot.getVehicles());
    }

}