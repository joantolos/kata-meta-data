package com.joantolos.kata.meta.data.extractor;

import com.joantolos.kata.meta.data.entity.BasicMetadata;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

public class BasicMetadataExtractorTest {

    private BasicMetaDataExtractor basicMetaDataExtractor;
    private String resourcesAbsolutePath;

    @Before
    public void setUp() throws URISyntaxException {
        URL res = getClass().getClassLoader().getResource("input/2020 - 1.jpg");
        File file = Paths.get(res.toURI()).toFile();
        this.resourcesAbsolutePath = file.getAbsolutePath().replace("/2020 - 1.jpg", "");
        this.basicMetaDataExtractor = new BasicMetaDataExtractor(this.resourcesAbsolutePath);
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
        Assert.assertNotNull(this.basicMetaDataExtractor.getResourceFolderFiles(this.resourcesAbsolutePath));
    }

    @Test
    public void shouldGetFileStream() {
        File[] files = this.basicMetaDataExtractor.getResourceFolderFiles(this.resourcesAbsolutePath);
        Assert.assertNotNull(this.basicMetaDataExtractor.getFileStream(files[0]));
    }

    @Test
    public void shouldGetMetadataList() {
        List<BasicMetadata> basicMetadata = this.basicMetaDataExtractor.getBasicMetadata();
        Assert.assertEquals(5, basicMetadata.size());
        Assert.assertEquals("/Users/joan/Documents/code/joantolos/kata-meta-data/build/resources/test/input/2020 - 1.jpg", basicMetadata.get(0).getFilePath());
    }
}
