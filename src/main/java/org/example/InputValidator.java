package org.example;

import org.example.exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;


public class InputValidator {
    public static void validateInput(String[] args) throws InvalidInputSizeException, FileNotFoundException, EmptyFileException, ProvidedFileIsDirectoryException, InvalidFileExtensionException, CannotReadFileException {
        checkInputParametersSize(args);

        for (String arg : args) {
            var file = new File(arg);

            checkFileExistence(file);
            checkIfIsFile(file);
            checkFileReadability(file);
            checkFileLength(file);
            checkFileExtension(file);
        }
    }

    private static void checkInputParametersSize(String[] args) throws InvalidInputSizeException {
        if (!isInputSizeValid(args.length)) throw new InvalidInputSizeException("No file was provided!");
    }

    private static void checkFileExtension(File file) throws InvalidFileExtensionException {
        if (!file.getName().endsWith(".txt"))
            throw new InvalidFileExtensionException("File '" + file.getName() + "' is not a .txt file");
    }

    private static void checkFileLength(File file) throws EmptyFileException {
        if (file.length() == 0) throw new EmptyFileException("File '" + file.getName() + "' is empty");
    }

    private static void checkFileReadability(File file) throws CannotReadFileException {
        if (!file.canRead()) throw new CannotReadFileException("File '" + file.getName() + "' cannot be read");
    }

    private static void checkIfIsFile(File file) throws ProvidedFileIsDirectoryException {
        if (!file.isFile()) throw new ProvidedFileIsDirectoryException("'" + file.getName() + "' is not a file");
    }

    private static void checkFileExistence(File file) throws FileNotFoundException {
        if (!file.exists()) throw new FileNotFoundException("File '" + file.getName() + "' was not found");
    }

    private static boolean isInputSizeValid(int argsQtd) {
        return argsQtd > 0;
    }
}
