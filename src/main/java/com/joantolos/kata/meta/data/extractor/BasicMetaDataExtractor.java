package com.joantolos.kata.meta.data.extractor;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Tag;
import com.joantolos.kata.meta.data.entity.BasicMetadata;
import com.joantolos.kata.meta.data.updater.DateUpdater;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class BasicMetaDataExtractor {

    private final String folderName;
    private String lastCreationDate = null;
    private final DateUpdater dateUpdater;
    private final FileCardinalExtractor fileCardinalExtractor;

    public BasicMetaDataExtractor(String folderName) {
        this.folderName = folderName;
        this.dateUpdater = new DateUpdater();
        this.fileCardinalExtractor = new FileCardinalExtractor();
    }

    public List<BasicMetadata> getBasicMetadata() {
        return Arrays.stream(this.getResourceFolderFiles(this.folderName))
                .map(metadata -> this.extract(metadata.getAbsolutePath(), getFileStream(metadata)))
                .sorted(Comparator.comparingInt(BasicMetadata::getFileCardinal))
                .map(this::updateCreationDate)
                .collect(Collectors.toList());
    }

    private InputStream getFileStream(File metadata) {
        return this.getClass().getResourceAsStream("/" + this.folderName + "/" + metadata.getName());
    }

    protected File[] getResourceFolderFiles(String folderName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(folderName);
        return new File(Objects.requireNonNull(url).getPath()).listFiles();
    }

    protected BasicMetadata extract(String fileName, InputStream file) {
        List<String> basicMetadata = new ArrayList<>();
        try {
            ImageMetadataReader.readMetadata(file).getDirectories().forEach(directory -> basicMetadata.addAll(getTags(directory)));
        } catch (ImageProcessingException | IOException ignored) {
        }
        return new BasicMetadata(this.fileCardinalExtractor.extract(fileName), fileName, getIsVideo(basicMetadata), this.getCreationDate(basicMetadata));
    }

    private BasicMetadata updateCreationDate(BasicMetadata basicMetadata) {
        if (basicMetadata.isVideo() && this.lastCreationDate != null) {
            this.lastCreationDate = this.dateUpdater.addOneMinute(this.lastCreationDate);
            basicMetadata.setCreationDate(this.lastCreationDate);
        } else {
            this.lastCreationDate = basicMetadata.getCreationDate();
        }
        return basicMetadata;
    }

    private List<String> getTags(Directory directory) {
        return directory.getTags().stream().map(Tag::toString).collect(Collectors.toList());
    }

    private boolean getIsVideo(List<String> basicMetadata) {
        return basicMetadata.get(0).contains("[MP4]");
    }

    private String getCreationDate(List<String> basicMetadata) {
        String dateMetadata = basicMetadata.stream().filter(meta -> meta.contains("[Exif SubIFD] Date/Time Original")).findFirst().orElse(null);
        return dateMetadata == null ? null : dateMetadata.replace("[Exif SubIFD] Date/Time Original - ", "");
    }
}
