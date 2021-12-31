package OOD.parkingLot;

import java.util.ArrayList;

public abstract class Vehicle {
    protected ArrayList<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>();
    protected String licensePlate;
    protected int spotsNeeded;
    protected VehicleSize size;

    public int getSpotsNeeded() {
        return spotsNeeded;
    }

    public VehicleSize getSize() {
        return size;
    }

    //Park vehicle in this spot (among others, potentially)
    public void parkInSpot(ParkingSpot spot) {
        parkingSpots.add(spot);
    }

    //remove car from spot, and notify spot that it's gone
    public void clearSpots() {
        for(int i = 0; i < parkingSpots.size(); i++) {
            parkingSpots.get(i).removeVehicle();
        }
        parkingSpots.clear();
    }

    //check if the spot is big enough for the vehicle(and is available).
    //This compares the size only. It does not check if it has enough spots.
    public abstract boolean canFitInSpot(ParkingSpot spot);
}
