package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnalyzerStartTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void givenNoFile_whenStart_thenThrowNoFileProvidedException() {
        AnalyzerStart.main(new String[]{});
        assertEquals("No file was provided!", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void givenFileDoesNotExist_whenStart_thenThrowFileNotFoundException() {
        String expectedMessage = "fileDoesNotExist.txt (O sistema não pode encontrar o arquivo especificado)";
        AnalyzerStart.main(new String[]{"fileDoesNotExist.txt"});
        assertEquals(expectedMessage, outputStreamCaptor.toString()
                .trim());
    }
}