package com.joantolos.kata.meta.data.entity;

public class BasicMetadata {

    private final int fileCardinal;
    private final String filePath;
    private final boolean isVideo;
    private String creationDate;

    public BasicMetadata(int fileCardinal, String filePath, boolean isVideo, String creationDate) {
        this.fileCardinal = fileCardinal;
        this.filePath = filePath;
        this.isVideo = isVideo;
        this.creationDate = creationDate;
    }

    public int getFileCardinal() {
        return fileCardinal;
    }

    public String getFilePath() {
        return filePath;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
