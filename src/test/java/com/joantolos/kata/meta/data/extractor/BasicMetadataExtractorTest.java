package com.joantolos.kata.meta.data.extractor;

import com.joantolos.kata.meta.data.entity.BasicMetadata;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class BasicMetadataExtractorTest {

    private BasicMetaDataExtractor basicMetaDataExtractor;

    @Before
    public void setUp() {
        this.basicMetaDataExtractor = new BasicMetaDataExtractor("input");
    }

    @Test
    public void shouldExtractBasicMetadataForPhoto() {
        InputStream file = this.getClass().getResourceAsStream("/input/2020 - 1.jpg");
        BasicMetadata basicMetadata = this.basicMetaDataExtractor.extract("2020 - 1.jpg", file);

        Assert.assertFalse(basicMetadata.isVideo());
        Assert.assertNotNull(basicMetadata.getCreationDate());
    }

    @Test
    public void shouldExtractBasicMetadataForVideo() {
        InputStream file = this.getClass().getResourceAsStream("/input/2020 - 3.m4v");
        BasicMetadata basicMetadata = this.basicMetaDataExtractor.extract("2020 - 3.m4v", file);

        Assert.assertTrue(basicMetadata.isVideo());
        Assert.assertNull(basicMetadata.getCreationDate());
    }

    @Test
    public void shouldGetFilesList() {
        Assert.assertNotNull(this.basicMetaDataExtractor.getResourceFolderFiles("input"));
    }

    @Test
    public void shouldGetMetadataList() {
        List<BasicMetadata> basicMetadata = this.basicMetaDataExtractor.getBasicMetadata();
        Assert.assertEquals(5, basicMetadata.size());
        Assert.assertEquals("/Users/joan/Documents/code/joantolos/kata-meta-data/build/resources/test/input/2020 - 1.jpg", basicMetadata.get(0).getFilePath());
    }
}
