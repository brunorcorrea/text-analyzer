package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class AnalyzerReader {
    private final TreeMap<String, ArrayList<String>> textTreeMap = new TreeMap<>();

    public String readFile(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            return getDataFromFile(fileReader);
        } catch (Exception e) {
            System.out.println("An error occurred while trying to read the file '" + file.getName() + "': \"" + e.getMessage() + "\"");
        }

        return null;
    }

    public String getDataFromFile(FileReader fileReader) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();

        try (var br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.equals("")) resultStringBuilder.append(line).append(" ");
            }
        }

        return resultStringBuilder.toString();
    }

    public String filterLettersAndNumbers(String text) {
        return text.replaceAll("[^\\pL0-9 ]", "");
    }

    public String formatLowerCase(String text) {
        return text.toLowerCase();
    }

    public String filterTextInvalidTokens(String text) {
        if (text == null) return null;
        String lowerCaseText = formatLowerCase(text);
        return filterLettersAndNumbers(lowerCaseText);
    }

    public void processText(String text) {
        String[] wordsList = text.split(" ");
        int lastWordIndex = wordsList.length - 1;

        for (int wordIndex = 0; wordIndex < lastWordIndex; wordIndex++) {
            String word = wordsList[wordIndex];
            var adjacencyList = textTreeMap.get(word);

            if (adjacencyList == null) // if word is not in the map, this means it is the first time it appears
                adjacencyList = new ArrayList<>();

            var nextWord = wordsList[wordIndex + 1];
            if (!adjacencyList.contains(nextWord)) // if the next word is not in the adjacency list, add it
                adjacencyList.add(nextWord);

            textTreeMap.put(word, adjacencyList);
        }
    }

    public TreeMap<String, ArrayList<String>> getTextTreeMap() {
        return textTreeMap;
    }

    public void clearData() {
        textTreeMap.clear();
    }
}
