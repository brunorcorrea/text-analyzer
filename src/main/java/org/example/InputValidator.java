package org.example;

import org.example.exceptions.InvalidInputSizeException;

import static org.example.messages.CustomTextAnalyzerMessages.MSG_NO_FILE_PROVIDED;

public class InputValidator {
    public static void validateInput(String[] args) throws InvalidInputSizeException {
        if (!isInputSizeValid(args.length)) throw new InvalidInputSizeException(MSG_NO_FILE_PROVIDED);
    }

    public static boolean isInputSizeValid(int argsQtd) {
        return argsQtd > 0;
    }
}
