package IdGenerator;

public class IdGenerator {

    private static int lastVehicleId;
    private int lastLotId, lastTerminalId;

    public IdGenerator() {
        lastVehicleId = 0;
        lastLotId = 0;
        lastTerminalId = 0;
    }

    public static int getNewVehicleId() {
        return ++lastVehicleId;
    }

    public int getNewLotId() {
        return ++lastLotId;
    }

    public int getNewTerminalId() { return ++lastTerminalId; }

}