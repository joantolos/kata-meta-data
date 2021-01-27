package com.joantolos.kata.meta.data.updater;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DateUpdaterTest {

    private DateUpdater dateUpdater;

    @Before
    public void setUp(){
        this.dateUpdater = new DateUpdater();
    }

    @Test
    public void shouldFormatExifToTouch() {
        Assert.assertEquals("202001111243.00", this.dateUpdater.exifFormatToTouchFormat("2020:01:11 12:43:00"));
    }

    @Test
    public void shouldAddOneMinute() {
        Assert.assertEquals("2020:01:11 12:44:00", this.dateUpdater.addOneMinute("2020:01:11 12:43:00"));
    }

    @Test
    public void shouldAddOneMinuteMinuteCornerCase() {
        Assert.assertEquals("2020:01:11 11:00:00", this.dateUpdater.addOneMinute("2020:01:11 10:59:00"));
    }

    @Test
    public void shouldAddOneMinuteHourCornerCase() {
        Assert.assertEquals("2020:01:13 12:00:00", this.dateUpdater.addOneMinute("2020:01:11 59:59:00"));
    }
}
