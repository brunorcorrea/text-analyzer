package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

public class AnalyzerWriter {
    private static Integer FILES_COUNT = 1;

    public void writeToFile(String text) {
        File file = new File(String.format("output-%d.csv", FILES_COUNT));
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(text);
            fileWriter.close();
            FILES_COUNT++;
        } catch (IOException e) {
            e.printStackTrace();
        }
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
