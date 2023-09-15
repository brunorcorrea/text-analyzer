package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

        assertEquals(expectedOutput, response);
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
}
