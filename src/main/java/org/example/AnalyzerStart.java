package org.example;

import org.example.exceptions.InvalidInputSizeException;

public class AnalyzerStart {
    public static void main(String[] args) {
        InputValidator inputValidator = new InputValidator();

        try {
            inputValidator.validateInput(args);

            AnalyzerController analyzerController = new AnalyzerController(inputValidator.getValidFiles());
            analyzerController.generateOutput();

        } catch (InvalidInputSizeException e) {
            System.out.println(e.getMessage() + " Please provide at least one file!");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred, stopping program! \n" + e.getMessage());
        }

    }
}