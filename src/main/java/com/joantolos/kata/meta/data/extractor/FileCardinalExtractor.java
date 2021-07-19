package com.joantolos.kata.meta.data.extractor;

public class FileCardinalExtractor {

    public int extract(String filePath) {
        String[] tokens = filePath.split("\\.(?=[^\\.]+$)");
        return Integer.parseInt(filePath.split(" - ")[1].substring(0, filePath.split(" - ")[1].length() - (tokens[1].length() + 1)));
    }
}
