package OOD.parkingLot;

public class ParkingLot {
    private Level[] levels;
    private final int NUM_LEVELS = 5;

    public ParkingLot() {
        levels = new Level[NUM_LEVELS];
        for(int i = 0; i < NUM_LEVELS; i++) {
            levels[i] = new Level(i, 30);
        }
    }

    //Park the Vehicle in a spot (or multiple spots). Return false if failed
    public boolean parkVehicle(Vehicle vehicle) {
        for(int i = 0; i < levels.length; i++) {
            if(levels[i].parkVehicle(vehicle)) {
                return true;
            }
        }
        return false;
    }
}
