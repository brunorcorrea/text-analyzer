package org.example;

import org.example.exceptions.InvalidInputSizeException;

import java.io.File;

public class AnalyzerStart {
    public static void main(String[] args) {

        try {
            InputValidator.validateInput(args);

            AnalyzerController analyzerController = new AnalyzerController(args);
            analyzerController.generateOutput();

        } catch (InvalidInputSizeException e) {
            System.out.println(e.getMessage()); //TODO treat better
        } catch (Exception e) {
            System.out.println(e.getMessage()); //TODO treat each exception
        }

    }
}