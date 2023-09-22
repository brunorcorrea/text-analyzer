package org.example.exceptions;

import java.io.Serial;

public class InvalidFileExtensionException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidFileExtensionException(String message) {
        super(message);
    }
}
