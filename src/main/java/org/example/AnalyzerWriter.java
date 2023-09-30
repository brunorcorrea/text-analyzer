package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

public class AnalyzerWriter {

    public void writeToFile(String text, File inputFile) {
        String fileName = inputFile.getName();
        File outputFile = new File(fileName.replace(".txt", ".csv"));

        try(FileWriter fileWriter = new FileWriter(outputFile)) {
            fileWriter.write(text);
        } catch (IOException e) {
            System.out.println("An error occurred while trying to write the file '" + outputFile.getName() + "': \"" + e.getMessage() + "\"");
        }
    }

    public String formatTreeMapToCSV(TreeMap<String, ArrayList<String>> text) {
        StringBuilder formattedText = new StringBuilder();
        String textSeparator = ", ";

        for (Entry<String, ArrayList<String>> entry : text.entrySet()) { //TODO percorre o mapa
            formattedText.append(entry.getKey());

            for (String value : entry.getValue()) {
                formattedText.append(textSeparator).append(value);
            } //TODO preenche a linha

            formattedText.append("\n");
        }

        return formattedText.toString();
    }
}
