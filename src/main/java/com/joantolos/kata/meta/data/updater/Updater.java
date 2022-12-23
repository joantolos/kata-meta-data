package com.joantolos.kata.meta.data.updater;

import com.joantolos.kata.meta.data.entity.Metadata;
import com.joantolos.kata.meta.data.extractor.MetaDataExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Updater {

    private final Logger log = LoggerFactory.getLogger(Updater.class);

    private final String inputFolder;
    private final DateUpdater dateUpdater;

    public Updater(String inputFolder) {
        this.inputFolder = inputFolder;
        this.dateUpdater = new DateUpdater();
    }

    public boolean update() {
        List<Metadata> metadata = new MetaDataExtractor(this.inputFolder).getBasicMetadata();

        metadata.forEach(metaData -> {
            log.info("########### Processing file: " + metaData.filePath());
            if (metaData.isVideo()) {
                log.info(". Is a video, updating its creation date to: " + metaData.creationDate());
                executeCommand("exiftool '-CreateDate=" + metaData.creationDate() + "' '" + metaData.filePath() + "'");
                new File(metaData.filePath() + "_original").delete();
            } else {
                log.info(". Is not a video");
            }
            executeCommand("touch -t " + this.dateUpdater.exifFormatToTouchFormat(metaData.creationDate()) + " '" + metaData.filePath() + "'");
        });
        return true;
    }

    private void executeCommand(String command) {
        log.info("Executing command: " + command);
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
