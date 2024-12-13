package com.baymotors.exceptions;

/**
 * Exception thrown when customer details are invalid.
 */
public class InvalidCustomerError extends Exception {
    public InvalidCustomerError(String message) {
        super(message);
    }
}