package org.example;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class AnalyzerWriter {
    public void writeToFile(String filePath, String text) {
        //TODO - Implement this method
    }

    public String formatTextToCSV(TreeMap<String, ArrayList<String>> text) {
        StringBuilder formattedText = new StringBuilder();
        String textSeparator = ", ";

        for (Entry<String, ArrayList<String>> entry : text.entrySet()) {
            formattedText.append(entry.getKey());

            for (String value : entry.getValue()) {
                formattedText.append(textSeparator).append(value);
            }

            formattedText.append("\n");
        }

        return formattedText.toString();
    }
}
