package com.joantolos.kata.meta.data;

import com.joantolos.kata.meta.data.updater.Updater;

public class MetaDataUpdater {

    public static final String INPUT_FOLDER_NAME = "input";

    public static void main(String[] args) {
        new Updater(INPUT_FOLDER_NAME).update();
    }
}
