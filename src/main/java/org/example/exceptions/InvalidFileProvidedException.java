package org.example.exceptions;

import java.io.Serial;

public class InvalidFileProvidedException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidFileProvidedException(String message) {
        super(message);
    }
}
