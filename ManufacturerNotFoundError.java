package com.baymotors.exceptions;

/**
 * Exception thrown when a manufacturer is not found.
 */
public class ManufacturerNotFoundError extends Exception {
    public ManufacturerNotFoundError(String message) {
        super(message);
    }
}