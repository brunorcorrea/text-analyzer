package org.example;

import org.example.exceptions.InvalidInputSizeException;

import java.io.File;

public class AnalyzerStart {
    public static void main(String[] args) {

        try {
            InputValidator.validateInput(args);

            for (String arg : args) {
                AnalyzerReader analyzerReader = new AnalyzerReader(); //TODO move to controller
                AnalyzerWriter analyzerWriter = new AnalyzerWriter(); //TODO move to controller

                analyzerReader.processText(analyzerReader.formatText(analyzerReader.readFile(arg)));
                analyzerWriter.writeToFile(analyzerWriter.formatTextToCSV(analyzerReader.getTextTreeMap()));
            }
        } catch (InvalidInputSizeException e) {
            System.out.println(e.getMessage()); //TODO treat better
        } catch (Exception e) {
            System.out.println(e.getMessage()); //TODO treat each exception
        }

    }
}