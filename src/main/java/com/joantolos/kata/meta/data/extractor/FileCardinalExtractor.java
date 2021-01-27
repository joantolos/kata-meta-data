package com.joantolos.kata.meta.data.extractor;

public class FileCardinalExtractor {

    public int extract(String fileName) {
        return Integer.parseInt(fileName.split(" - ")[1].substring(0, fileName.split(" - ")[1].length() - 4));
    }
}
