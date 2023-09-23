package org.example;

import java.io.File;

public class AnalyzerController {
    private final String[] files;
    private final AnalyzerReader analyzerReader = new AnalyzerReader();
    private final AnalyzerWriter analyzerWriter = new AnalyzerWriter();

    public AnalyzerController(String[] files) {
        this.files = files;
    }

    public void generateOutput() throws Exception {
        for (String arg : this.files) {
            File file = new File(arg);
            analyzerReader.processText(analyzerReader.formatText(analyzerReader.readFile(arg)));
            analyzerWriter.writeToFile(analyzerWriter.formatTextToCSV(analyzerReader.getTextTreeMap()), file.getName());
        }
    }
}
