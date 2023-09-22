package org.example.exceptions;

import java.io.Serial;

public class EmptyFileException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public EmptyFileException(String message) {
        super(message);
    }
}
