package com.baymotors.exceptions;

/**
 * Exception thrown when a vehicle is not found.
 */
public class VehicleNotFoundError extends Exception {
    public VehicleNotFoundError(String message) {
        super(message);
    }
}