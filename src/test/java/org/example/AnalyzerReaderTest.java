package org.example;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnalyzerReaderTest {
    public final AnalyzerReader analyzerReader = new AnalyzerReader();
    public final AnalyzerWriter analyzerWriter = new AnalyzerWriter();

    @ParameterizedTest
    @CsvSource(value = {"'validation-files/happy-birthday-input.txt', 'validation-files/happy-birthday-output.txt'"
            , "'validation-files/luar-sertao-input.txt', 'validation-files/luar-sertao-output.txt'"
    })
    public void givenValidText_thenReturnExpected(String filePath, String expectedOutputPath) throws Exception {
        String expectedOutput = readFile(expectedOutputPath);

        String text = analyzerReader.readFile(filePath);
        analyzerReader.processText(analyzerReader.formatText(text));
        String response = analyzerWriter.formatTextToCSV(analyzerReader.getTextTreeMap());


        assertProcessedTextIsEqualToExpected(response, expectedOutput);
    }

    @Test
    public void givenTextWithSymbols_thenReturnOnlyLettersAndNumbers() {
        String text = "Hello, world! 123-3240.534. Brunão, teste de acentuação, ç, á";
        String expectedText = "Hello world 1233240534 Brunão teste de acentuação ç á";

        String response = analyzerReader.filterLettersAndNumbers(text);

        assertEquals(expectedText, response);
    }

    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    private String readFile(String filePath) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(filePath)) {
            return readFromInputStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new Exception("File not found");
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
