package org.example.exceptions;

import java.io.Serial;

public class ProvidedFileIsDirectoryException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public ProvidedFileIsDirectoryException(String message) {
        super(message);
    }
}
