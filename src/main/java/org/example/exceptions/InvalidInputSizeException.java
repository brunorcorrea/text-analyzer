package org.example.exceptions;

import java.io.Serial;

public class InvalidInputSizeException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidInputSizeException(String message) {
        super(message);
    }
}
