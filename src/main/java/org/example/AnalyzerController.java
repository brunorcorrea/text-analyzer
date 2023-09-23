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
            analyzerReader.processText(analyzerReader.formatText(analyzerReader.readFile(file)));
            analyzerWriter.writeToFile(analyzerWriter.formatTextToCSV(analyzerReader.getTextTreeMap()), file.getName());
        }
    }
}
