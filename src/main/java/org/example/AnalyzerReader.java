package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class AnalyzerReader {
    private final TreeMap<String, ArrayList<String>> textTreeMap = new TreeMap<>();

    public String readFile(File file) throws Exception {
        try(FileReader fileReader = new FileReader(file)) {
            return getDataFromFile(fileReader);
        }
    }

    public String getDataFromFile(FileReader fileReader) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (var br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append(" ");
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

    public String formatText(String text) {
        String lowerCaseText = formatLowerCase(text);
        return filterLettersAndNumbers(lowerCaseText);
    }

    public void processText(String text) {
        ArrayList<String> wordsList = new ArrayList<>(Arrays.asList(text.split(" ")));

        int contador = 0;

        for (String w : wordsList) {
            if (contador != wordsList.size() - 1) {
                if (textTreeMap.get(w) == null) {
                    var adjacencyList = new ArrayList<String>();
                    adjacencyList.add(wordsList.get(contador + 1));
                    textTreeMap.put(w, adjacencyList);
                } else {
                    var adjacencyList = textTreeMap.get(w);
                    var nextWord = wordsList.get(contador + 1);

                    if (!adjacencyList.contains(nextWord)) {
                        adjacencyList.add(nextWord);
                    }

                    textTreeMap.put(w, adjacencyList);
                }
            }
            contador++;
        }
    }

    public TreeMap<String, ArrayList<String>> getTextTreeMap() {
        return textTreeMap;
    }
}
