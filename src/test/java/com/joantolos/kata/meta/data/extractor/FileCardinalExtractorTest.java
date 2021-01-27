package com.joantolos.kata.meta.data.extractor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileCardinalExtractorTest {

    private FileCardinalExtractor fileCardinalExtractor;

    @Before
    public void setUp() {
        this.fileCardinalExtractor = new FileCardinalExtractor();
    }

    @Test
    public void shouldExtractCardinalNumberFromFileName() {
        Assert.assertEquals(355, this.fileCardinalExtractor.extract("2020 - 355.jpg"));
    }
}
