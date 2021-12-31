package OOD.parkingLot;

public class ParkingSpot {
    private Vehicle vehicle;
    private VehicleSize spotSize;
    private int row;
    private Level level;
    private int spotNumber;

    public ParkingSpot(Level level, int row, int spotNumber, VehicleSize size) {
        this.level = level;
        this.row = row;
        this.spotNumber = spotNumber;
        spotSize = size;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    /* Checks if the spot is big enough for the vehicle (and is available.) This compares
    the size only. It does not check if it has enough spots.
     */
    public boolean canFitVehicle(Vehicle vehicle) {
        return isAvailable() && vehicle.canFitInSpot(this);
    }

    /* Park vehicle in the spot. */
    public boolean park(Vehicle vehicle) {
        if(!canFitVehicle(vehicle)) {
            return false;
        }
        this.vehicle = vehicle;
        this.vehicle.parkInSpot(this);
        return true;
    }

    public int getRow() {
        return row;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public VehicleSize getSize() {
        return spotSize;
    }

    /* Remove vehicle from spot, and notify level that a new spot is available*/
    public void removeVehicle() {
        level.spotFreed();
        vehicle = null;
    }

}
