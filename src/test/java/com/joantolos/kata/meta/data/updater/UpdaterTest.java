package com.joantolos.kata.meta.data.updater;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class UpdaterTest {

    private final Updater updater;

    public UpdaterTest() throws URISyntaxException {
        URL res = getClass().getClassLoader().getResource("input/2020 - 1.jpg");
        File file = Paths.get(res.toURI()).toFile();
        this.updater = new Updater(file.getAbsolutePath().replace("/2020 - 1.jpg", ""));
    }

    @Test
    public void shouldUpdate() {
        Assertions.assertTrue(this.updater.update());
    }
}
