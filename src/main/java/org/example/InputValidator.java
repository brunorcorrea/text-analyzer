package org.example;

import org.example.exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class InputValidator {
    private final ArrayList<File> validFiles = new ArrayList<>();

    public void validateInput(String[] args) throws InvalidInputSizeException {
        checkInputParametersSize(args);

        for (String arg : args) {
            try {
                var file = new File(getFilePath(arg));

                checkFileExistence(file);
                checkIfIsFile(file);
                checkFileReadability(file);
                checkFileLength(file);
                checkFileExtension(file);

                validFiles.add(file);
            } catch (Exception e) {
                System.out.println("An error occurred while validating the file '" + arg + "': \"" + e.getMessage() + "\"");
            }
        }
    }

    public ArrayList<File> getValidFiles() {
        return validFiles;
    }

    private String getFilePath(String fileName) {
        if (fileName.matches("^[A-Za-z]:[\\\\/].*")) // verifies if file starts like C:\ or C:/
            return fileName;

        String rootFolder = System.getProperty("user.dir");
        if (fileName.startsWith("/"))
            return rootFolder + fileName;
        else if (fileName.startsWith("\\"))
            return rootFolder + fileName;
        else if (rootFolder.contains("/"))
            return rootFolder + "/" + fileName;

        return rootFolder + "\\" + fileName;
    }

    private void checkInputParametersSize(String[] args) throws InvalidInputSizeException {
        if (!isInputSizeValid(args.length)) throw new InvalidInputSizeException("No file was provided!");
    }

    private void checkFileExtension(File file) throws InvalidFileExtensionException {
        if (!file.getName().endsWith(".txt"))
            throw new InvalidFileExtensionException("File '" + file.getName() + "' is not a .txt file!");
    }

    private void checkFileLength(File file) throws EmptyFileException {
        if (file.length() == 0) throw new EmptyFileException("File '" + file.getName() + "' is empty!");
    }

    private void checkFileReadability(File file) throws CannotReadFileException {
        if (!file.canRead()) throw new CannotReadFileException("File '" + file.getName() + "' cannot be read!");
    }

    private void checkIfIsFile(File file) throws ProvidedFileIsDirectoryException {
        if (!file.isFile()) throw new ProvidedFileIsDirectoryException("'" + file.getName() + "' is not a file!");
    }

    private void checkFileExistence(File file) throws FileNotFoundException {
        if (!file.exists()) throw new FileNotFoundException("File '" + file.getName() + "' was not found!");
    }

    private boolean isInputSizeValid(int argsQtd) {
        return argsQtd > 0;
    }
}
