package org.example;

import java.io.*;

public class AnalyzerReader {
    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (var br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    public String readFile(String filePath) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        try (var inputStream = classLoader.getResourceAsStream(filePath)) {
            return readFromInputStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new Exception("File not found"); //TODO - Create a custom exception named 'FileNotFound'
    }

    public String filterLettersAndNumbers(String text) {
        return text.replaceAll("[^\\pL0-9 ]", "");
    }
}
