package com.baymotors.exceptions;

/**
 * Exception thrown when a mechanic is not found.
 */
public class MechanicNotFoundError extends Exception {
    public MechanicNotFoundError(String message) {
        super(message);
    }
}