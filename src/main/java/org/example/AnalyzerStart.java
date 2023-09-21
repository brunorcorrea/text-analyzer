package org.example;

import org.example.exceptions.InvalidInputSizeException;

import java.io.File;

public class AnalyzerStart {
    public static void main(String[] args) {

        try {
            InputValidator.validateInput(args);

            for (String arg : args) {
                File file = new File(arg + "mwda");
                if(!file.exists())
                    throw new Exception("File 2 not found");
//                AnalyzerReader analyzerReader = new AnalyzerReader();
//                AnalyzerWriter analyzerWriter = new AnalyzerWriter();
//
//                analyzerReader.processText(analyzerReader.formatText(analyzerReader.readFile(arg)));
//                analyzerWriter.writeToFile(analyzerWriter.formatTextToCSV(analyzerReader.getTextTreeMap()));
            }
        } catch (InvalidInputSizeException e) {
            System.out.println(e.getMessage()); //TODO treat better
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}