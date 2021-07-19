package com.joantolos.kata.meta.data.extractor;

public class FileCardinalExtractor {

    public int extract(String filePath) {
        String extension = filePath.split("\\.(?=[^\\.]+$)")[1];
        return Integer.parseInt(filePath.split(" - ")[1].substring(0, filePath.split(" - ")[1].length() - (extension.length() + 1)));
    }
}
