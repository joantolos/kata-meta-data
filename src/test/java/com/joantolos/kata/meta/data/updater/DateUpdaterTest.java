package com.joantolos.kata.meta.data.updater;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateUpdaterTest {

    private final DateUpdater dateUpdater = new DateUpdater();

    @Test
    public void shouldFormatExifToTouch() {
        Assertions.assertEquals("202001111243.00", this.dateUpdater.exifFormatToTouchFormat("2020:01:11 12:43:00"));
    }

    @Test
    public void shouldAddOneMinute() {
        Assertions.assertEquals("2020:01:11 12:44:00", this.dateUpdater.addOneMinute("2020:01:11 12:43:00"));
    }

    @Test
    public void shouldAddOneMinuteMinuteCornerCase() {
        Assertions.assertEquals("2020:01:11 11:00:00", this.dateUpdater.addOneMinute("2020:01:11 10:59:00"));
    }

    @Test
    public void shouldAddOneMinuteHourCornerCase() {
        Assertions.assertEquals("2020:01:13 12:00:00", this.dateUpdater.addOneMinute("2020:01:11 59:59:00"));
    }
}
