package org.example.exceptions;

import java.io.Serial;

public class NoFileProvidedException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public NoFileProvidedException(String message) {
        super(message);
    }
}
