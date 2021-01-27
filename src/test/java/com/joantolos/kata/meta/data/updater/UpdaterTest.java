package com.joantolos.kata.meta.data.updater;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UpdaterTest {

    private Updater updater;

    @Before
    public void init() {
        this.updater = new Updater("input");
    }

    @Test
    public void shouldUpdate() {
        Assert.assertTrue(this.updater.update());
    }
}
