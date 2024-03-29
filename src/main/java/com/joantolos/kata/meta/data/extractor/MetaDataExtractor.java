package com.joantolos.kata.meta.data.extractor;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Tag;
import com.joantolos.kata.meta.data.entity.Metadata;
import com.joantolos.kata.meta.data.updater.DateUpdater;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class MetaDataExtractor {

    private final String folderName;
    private String lastCreationDate = null;
    private final DateUpdater dateUpdater;
    private final FileCardinalExtractor fileCardinalExtractor;

    public MetaDataExtractor(String folderName) {
        this.folderName = folderName;
        this.dateUpdater = new DateUpdater();
        this.fileCardinalExtractor = new FileCardinalExtractor();
    }

    public List<Metadata> getBasicMetadata() {
        return Arrays.stream(this.getResourceFolderFiles(this.folderName))
                .filter(metadata -> !metadata.getName().contains(".DS"))
                .map(metadata -> this.extract(metadata.getAbsolutePath(), getFileStream(metadata)))
                .sorted(Comparator.comparingInt(Metadata::fileCardinal))
                .map(this::updateCreationDate)
                .collect(Collectors.toList());
    }

    protected File[] getResourceFolderFiles(String inputFolderPath) {
        return new File(inputFolderPath).listFiles();
    }

    protected InputStream getFileStream(File metadata) {
        try {
            return new FileInputStream(metadata.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected Metadata extract(String fileName, InputStream file) {
        List<String> basicMetadata = new ArrayList<>();
        try {
            ImageMetadataReader.readMetadata(file).getDirectories().forEach(directory -> basicMetadata.addAll(getTags(directory)));
        } catch (ImageProcessingException | IOException ignored) {
        }
        return new Metadata(this.fileCardinalExtractor.extract(fileName), fileName, getIsVideo(basicMetadata), this.getCreationDate(basicMetadata));
    }

    private Metadata updateCreationDate(Metadata metadata) {
        if (metadata.isVideo() && this.lastCreationDate != null) {
            this.lastCreationDate = this.dateUpdater.addOneMinute(this.lastCreationDate);
            return new Metadata(metadata.fileCardinal(), metadata.filePath(), metadata.isVideo(), this.lastCreationDate);
        } else {
            this.lastCreationDate = metadata.creationDate();
        }
        return metadata;
    }

    private List<String> getTags(Directory directory) {
        return directory.getTags().stream().map(Tag::toString).collect(Collectors.toList());
    }

    private boolean getIsVideo(List<String> basicMetadata) {
        return basicMetadata.get(0).contains("[MP4]") || basicMetadata.get(0).contains("[QuickTime]");
    }

    private String getCreationDate(List<String> basicMetadata) {
        String dateMetadata = basicMetadata.stream().filter(meta -> meta.contains("[Exif SubIFD] Date/Time Original")).findFirst().orElse(null);
        return dateMetadata == null ? null : dateMetadata.replace("[Exif SubIFD] Date/Time Original - ", "");
    }
}

