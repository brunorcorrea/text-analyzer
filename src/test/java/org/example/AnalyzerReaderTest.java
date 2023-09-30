package org.example;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnalyzerReaderTest {
    public final AnalyzerReader analyzerReader = new AnalyzerReader();
    public final AnalyzerWriter analyzerWriter = new AnalyzerWriter();

    @ParameterizedTest
    @CsvSource(value = {"'D:\\Bruno\\Documents\\GitHub\\text-analyzer\\validation-files\\happy-birthday-input.txt', 'D:\\Bruno\\Documents\\GitHub\\text-analyzer\\validation-files\\happy-birthday-output.csv'"
            , "'validation-files\\luar-sertao-input.txt', 'validation-files\\luar-sertao-output.csv'"
    })
    public void givenValidText_thenReturnExpected(String filePath, String expectedOutputPath) throws Exception {
        String expectedOutput = readFile(expectedOutputPath);

        File file = new File(getFilePath(filePath));
        String text = analyzerReader.readFile(file);
        analyzerReader.processText(analyzerReader.filterTextInvalidTokens(text));
        String response = analyzerWriter.formatTreeMapToCSV(analyzerReader.getTextTreeMap());


        assertProcessedTextIsEqualToExpected(response, expectedOutput);
    }

    @Test
    public void givenTextWithSymbols_thenReturnOnlyLettersAndNumbers() {
        String text = "Hello, world! 123-3240.534. Brunão, teste de acentuação, ç, á";
        String expectedText = "Hello world 1233240534 Brunão teste de acentuação ç á";

        String response = analyzerReader.filterLettersAndNumbers(text);

        assertEquals(expectedText, response);
    }

    private String getFilePath(String fileName) {
        if (fileName.matches("^[A-Za-z]:[\\\\/].*"))
            return fileName;

        String rootFolder = System.getProperty("user.dir");
        if (fileName.startsWith("/"))
            return rootFolder + fileName;
        else if (fileName.startsWith("\\"))
            return rootFolder + fileName;
        else if (rootFolder.contains("/"))
            return rootFolder + "/" + fileName;

        return rootFolder + "\\" + fileName;
    }

    public String readFile(String fileName) throws Exception {
        File file = new File(getFilePath(fileName));

        try (FileReader fileReader = new FileReader(file)) {
            return getDataFromFile(fileReader);
        }
    }

    public String getDataFromFile(FileReader fileReader) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (var br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    private void assertProcessedTextIsEqualToExpected(String processedText, String expectedText) {
        String[] textLines = processedText.split("\n");
        TreeMap<String, ArrayList<String>> processedTextTreeMap = new TreeMap<>();
        Arrays.stream(textLines).forEach(line -> {
            String[] lineValues = line.split(", ");
            processedTextTreeMap.put(lineValues[0], new ArrayList<>(Arrays.asList(lineValues)));
        });

        String[] expectedTextLines = expectedText.split("\n");
        TreeMap<String, ArrayList<String>> expectedTextTreeMap = new TreeMap<>();
        Arrays.stream(expectedTextLines).forEach(line -> {
            String[] lineValues = line.split(", ");
            expectedTextTreeMap.put(lineValues[0], new ArrayList<>(Arrays.asList(lineValues)));
        });

        assertEquals(processedTextTreeMap.keySet(), expectedTextTreeMap.keySet());
        for (String key : processedTextTreeMap.keySet()) {
            assertThat(processedTextTreeMap.get(key), Matchers.containsInAnyOrder(expectedTextTreeMap.get(key).toArray()));
        }
    }
}
