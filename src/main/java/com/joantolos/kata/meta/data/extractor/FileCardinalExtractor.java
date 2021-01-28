package com.joantolos.kata.meta.data.extractor;

public class FileCardinalExtractor {

    public int extract(String filePath) {
        int cardinal = Integer.parseInt(filePath.split(" - ")[1].substring(0, filePath.split(" - ")[1].length() - 4));
        return cardinal;
    }
}
