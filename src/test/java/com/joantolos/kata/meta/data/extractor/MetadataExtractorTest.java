package com.joantolos.kata.meta.data.extractor;

import com.joantolos.kata.meta.data.entity.Metadata;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

public class MetadataExtractorTest {

    private final MetaDataExtractor metaDataExtractor;
    private final String resourcesAbsolutePath;

    public MetadataExtractorTest() throws URISyntaxException {
        URL res = getClass().getClassLoader().getResource("input/2020 - 1.jpg");
        File file = Paths.get(res.toURI()).toFile();
        this.resourcesAbsolutePath = file.getAbsolutePath().replace("/2020 - 1.jpg", "");
        this.metaDataExtractor = new MetaDataExtractor(this.resourcesAbsolutePath);
    }

    @Test
    public void shouldExtractBasicMetadataForPhoto() {
        InputStream file = this.getClass().getResourceAsStream("/input/2020 - 1.jpg");
        Metadata metadata = this.metaDataExtractor.extract("2020 - 1.jpg", file);

        Assertions.assertFalse(metadata.isVideo());
        Assertions.assertNotNull(metadata.creationDate());
    }

    @Test
    public void shouldExtractBasicMetadataForVideo() {
        InputStream file = this.getClass().getResourceAsStream("/input/2020 - 3.m4v");
        Metadata metadata = this.metaDataExtractor.extract("2020 - 3.m4v", file);

        Assertions.assertTrue(metadata.isVideo());
        Assertions.assertNull(metadata.creationDate());
    }

    @Test
    public void shouldGetFilesList() {
        Assertions.assertNotNull(this.metaDataExtractor.getResourceFolderFiles(this.resourcesAbsolutePath));
    }

    @Test
    public void shouldGetFileStream() {
        File[] files = this.metaDataExtractor.getResourceFolderFiles(this.resourcesAbsolutePath);
        Assertions.assertNotNull(this.metaDataExtractor.getFileStream(files[0]));
    }

    @Test
    public void shouldGetMetadataList() {
        List<Metadata> metadata = this.metaDataExtractor.getBasicMetadata();
        Assertions.assertEquals(5, metadata.size());
    }
}
