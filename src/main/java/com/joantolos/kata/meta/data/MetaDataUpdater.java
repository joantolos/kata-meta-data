package com.joantolos.kata.meta.data;

import com.joantolos.kata.meta.data.updater.Updater;

public class MetaDataUpdater {

    public static void main(String[] args) {
        if (args == null) {
            throw new IllegalArgumentException("Input folder path must be provided");
        }
        new Updater(args[0]).update();
    }
}
