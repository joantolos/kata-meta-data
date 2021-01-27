package com.joantolos.kata.meta.data.updater;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUpdater {

    public static final int ONE_MINUTE = 60000;

    public String exifFormatToTouchFormat(String exifFormatDate) {
        StringBuilder str = new StringBuilder(exifFormatDate.replace(":", "").replace(" ", ""));
        str.insert(str.length()-2,'.');
        return str.toString();
    }

    public String addOneMinute(String creationDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
            Calendar date = Calendar.getInstance();
            date.setTime(dateFormat.parse(creationDate));
            return dateFormat.format(new Date(date.getTimeInMillis() + ONE_MINUTE));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
