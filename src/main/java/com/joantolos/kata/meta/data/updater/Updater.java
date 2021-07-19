package com.joantolos.kata.meta.data.updater;

import com.joantolos.kata.meta.data.entity.BasicMetadata;
import com.joantolos.kata.meta.data.extractor.BasicMetaDataExtractor;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Updater {

    private final String inputFolder;
    private final DateUpdater dateUpdater;

    public Updater(String inputFolder) {
        this.inputFolder = inputFolder;
        this.dateUpdater = new DateUpdater();
    }

    public boolean update() {
        List<BasicMetadata> basicMetadata = new BasicMetaDataExtractor(this.inputFolder).getBasicMetadata();

        basicMetadata.forEach(metaData -> {
            System.out.print("########### Processing file: " + metaData.getFilePath());
            if (metaData.isVideo()) {
                System.out.println(". Is a video, updating it's creation date to: " + metaData.getCreationDate());
                executeCommand("exiftool '-CreateDate=" + metaData.getCreationDate() + "' '" + metaData.getFilePath() + "'");
                new File(metaData.getFilePath() + "_original").delete();
            } else {
                System.out.println(". Is not a video");
            }
            executeCommand("touch -t " + this.dateUpdater.exifFormatToTouchFormat(metaData.getCreationDate()) + " '" + metaData.getFilePath() + "'");
        });
        return true;
    }

    private void executeCommand(String command) {
        System.out.println("Executing command: " + command);
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", command);

        try {
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
