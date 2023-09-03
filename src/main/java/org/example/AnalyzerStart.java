package org.example;

import org.example.exceptions.NoFileProvidedException;

import static org.example.messages.CustomTextAnalyzerMessages.MSG_NO_FILE_PROVIDED;

public class AnalyzerStart {
    public static void main(String[] args) {
        try {
            inputValidator(args);
        } catch (NoFileProvidedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void inputValidator(String[] args) throws NoFileProvidedException {
        if (args.length == 0) {
            throw new NoFileProvidedException(MSG_NO_FILE_PROVIDED);
        }
    }
}