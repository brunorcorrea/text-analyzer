package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

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
        var expectedMessages = List.of("fileDoesNotExist.txt (O sistema não pode encontrar o arquivo especificado)", "File 'fileDoesNotExist.txt' was not found");
        AnalyzerStart.main(new String[]{"fileDoesNotExist.txt"});
        try{

            assertEquals(expectedMessages.get(0), outputStreamCaptor.toString()
                    .trim());
        } catch (AssertionError e) {
            assertEquals(expectedMessages.get(1), outputStreamCaptor.toString()
                    .trim());
        }
    }
}