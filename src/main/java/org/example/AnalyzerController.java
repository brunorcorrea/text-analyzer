package org.example;

import java.io.File;
import java.util.ArrayList;

public class AnalyzerController {
    private final ArrayList<File> files;
    private final AnalyzerReader analyzerReader = new AnalyzerReader();
    private final AnalyzerWriter analyzerWriter = new AnalyzerWriter();

    public AnalyzerController(ArrayList<File> files) {
        this.files = files;
    }

    public void generateOutput() {
        for (File file : this.files) {
            String text = analyzerReader.readFile(file);
            if(text == null) continue;

            String filteredText = analyzerReader.filterTextInvalidTokens(text);
            analyzerReader.processText(filteredText);

            analyzerWriter.writeToFile(getCSVFormattedText(), file);
        }
    }

    private String getCSVFormattedText() {
        var textTreeMap = analyzerReader.getTextTreeMap();
        return analyzerWriter.formatTreeMapToCSV(textTreeMap);
    }
}
