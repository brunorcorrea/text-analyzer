package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

public class AnalyzerWriter {

    public void writeToFile(String text, String fileName) {
        var outputFile = new File(fileName);

        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            fileWriter.write(text);
        } catch (IOException e) {
            System.out.println("An error occurred while trying to write the file '" + outputFile.getName() + "': \"" + e.getMessage() + "\"");
        }
    }

    public String formatTreeMapToCSV(TreeMap<String, ArrayList<String>> text) {
        StringBuilder formattedText = new StringBuilder();
        String textSeparator = ", ";

        for (Entry<String, ArrayList<String>> entry : text.entrySet()) { // for each word in the map
            formattedText.append(entry.getKey());

            for (String value : entry.getValue()) { // for each word in the adjacency list
                formattedText.append(textSeparator).append(value);
            }

            formattedText.append("\n");
        }

        return formattedText.toString();
    }
}
