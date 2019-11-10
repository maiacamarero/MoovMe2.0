import java.util.HashMap;

public class ZoneDatabase {

    private HashMap<String, Zone> zones;

    public ZoneDatabase(HashMap<String, Zone> zones) {
        this.zones = zones;
    }

    public Zone findZone(String zoneName) {
        return zones.get(zoneName.toLowerCase());
    }

}