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

    public void generateOutput() throws Exception {
        for (File file : this.files) {
            analyzerReader.processText(filterTextInvalidTokens(file));
            analyzerWriter.writeToFile(getCSVFormattedText(), file);
        }
    }

    private String filterTextInvalidTokens(File file) throws Exception {
        String text = analyzerReader.readFile(file);
        return analyzerReader.formatText(text);
    }

    private String getCSVFormattedText() {
        var textTreeMap = analyzerReader.getTextTreeMap();
        return analyzerWriter.formatTreeMapToCSV(textTreeMap);
    }
}
