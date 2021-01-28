package com.joantolos.kata.meta.data.updater;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class UpdaterTest {

    private Updater updater;
    private String resourcesAbsolutePath;

    @Before
    public void init() throws URISyntaxException {
        URL res = getClass().getClassLoader().getResource("input/2020 - 1.jpg");
        File file = Paths.get(res.toURI()).toFile();
        this.resourcesAbsolutePath = file.getAbsolutePath().replace("/2020 - 1.jpg", "");
        this.updater = new Updater(resourcesAbsolutePath);
    }

    @Test
    public void shouldUpdate() {
        Assert.assertTrue(this.updater.update());
    }
}
