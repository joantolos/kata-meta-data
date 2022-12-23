package com.joantolos.kata.meta.data.extractor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileCardinalExtractorTest {

    private final FileCardinalExtractor fileCardinalExtractor = new FileCardinalExtractor();

    @Test
    public void shouldExtractCardinalNumberFromFileName() {
        Assertions.assertEquals(355, fileCardinalExtractor.extract("2020 - 355.jpg"));
    }

    @Test
    public void shouldExtractCardinalNumberFromFileNameNoMatterTheExtension() {
        Assertions.assertEquals(22, fileCardinalExtractor.extract("2021 - 22.jpeg"));
    }
}
