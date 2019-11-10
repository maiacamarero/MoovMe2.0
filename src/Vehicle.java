public class Vehicle {

    private TypeOfVehicle typeOfVehicle;
    private int vehicleId, lotId, totalMinuteFare;
    private Zone zone;

    public Vehicle(TypeOfVehicle typeOfVehicle, int vehicleId) {
        this.typeOfVehicle = typeOfVehicle;
        this.vehicleId = vehicleId;
    }

    public TypeOfVehicle getTypeOfVehicle() {
        return typeOfVehicle;
    }

    int getVehicleId() {
        return vehicleId;
    }
}
