package org.example.exceptions;

import java.io.Serial;

public class CannotReadFileException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public CannotReadFileException(String message) {
        super(message);
    }
}
