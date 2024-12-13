package com.baymotors.vehicles;

/**
 * Represents a vehicle registered in the system.
 */
public class VehicleEntity {
    private int vehicleId;
    private String make;
    private String model;
    private int year;

    public VehicleEntity(int vehicleId, String make, String model, int year) {
        this.vehicleId = vehicleId;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "VehicleEntity{" +
                "vehicleId=" + vehicleId +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}