package org.example;

import org.example.exceptions.NoFileProvidedException;

import static org.example.messages.CustomTextAnalyzerMessages.MSG_NO_FILE_PROVIDED;

public class AnalyzerStart {
    public static void main(String[] args) {

        for (String arg : args) {
            try {
                inputValidator(args);
                AnalyzerReader analyzerReader = new AnalyzerReader();
                AnalyzerWriter analyzerWriter = new AnalyzerWriter();

                analyzerReader.processText(analyzerReader.formatText(analyzerReader.readFile("validation-files/happy-birthday-input.txt")));
                analyzerWriter.writeToFile(analyzerWriter.formatTextToCSV(analyzerReader.getTextTreeMap()));

            } catch (NoFileProvidedException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static void inputValidator(String[] args) throws NoFileProvidedException {
        if (args.length == 0) {
            throw new NoFileProvidedException(MSG_NO_FILE_PROVIDED);
        }
    }
}