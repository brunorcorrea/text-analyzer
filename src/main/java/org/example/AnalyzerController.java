package org.example;

import java.io.File;
import java.util.ArrayList;

public class AnalyzerController {
    private final ArrayList<File> files;
    private final AnalyzerReader analyzerReader = new AnalyzerReader();
    private final AnalyzerWriter analyzerWriter = new AnalyzerWriter();

    public AnalyzerController(ArrayList<File> files) {
        this.files = files;
    }

    public void generateOutput() {
        printSeparator();

        for (File file : this.files) {
            String text = readFile(file);
            if (text == null) continue;

            processText(text, file.getName());

            writeFile(generateOutputFileName(file));

            clearData();

            printSeparator();
        }
    }

    private String readFile(File file) {
        System.out.println("Reading file '" + file.getName() + "'...");
        String text = analyzerReader.readFile(file);
        System.out.println("File '" + file.getName() + "' read successfully!\n");

        return text;
    }

    private void processText(String text, String fileName) {
        System.out.println("Processing file '" + fileName + "'...");
        String filteredText = analyzerReader.filterTextInvalidTokens(text);
        analyzerReader.processText(filteredText);
        System.out.println("File '" + fileName + "' processed successfully!\n");
    }

    private void writeFile(String fileName) {
        System.out.println("Writing file '" + fileName + "'...");
        analyzerWriter.writeToFile(getCSVFormattedText(), fileName);
        System.out.println("File '" + fileName + "' written successfully!\n");
    }

    private String getCSVFormattedText() {
        var textTreeMap = analyzerReader.getTextTreeMap();
        return analyzerWriter.formatTreeMapToCSV(textTreeMap);
    }

    private String generateOutputFileName(File inputFile) {
        return inputFile.getName().replace(".txt", ".csv");
    }

    private void clearData() {
        analyzerReader.clearData();
    }

    private void printSeparator() {
        System.out.println("------------------------------------------------------------------\n");
    }
}
