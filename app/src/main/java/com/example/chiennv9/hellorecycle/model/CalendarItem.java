package com.example.chiennv9.hellorecycle.model;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.TimeZone;

public class CalendarItem {

    public static final String DEFAULT_FORMAT_TEXT_TOP = "MMM";
    public static final String DEFAULT_FORMAT_TEXT_MIDDLE = "dd";
    public static final String DEFAULT_FORMAT_TEXT_BOTTOM = "EEE";

    private String date;
    private String month;
    private String day;
    private int position;
    private Calendar calendar;

    public CalendarItem(int position, Calendar calendar) {
        this.position = position;

        long currentTime = position * 24 * 60 * 60 + calendar.getTimeInMillis();
        calendar.setTimeInMillis(currentTime);

       /* setDate(DateFormat.format(DEFAULT_FORMAT_TEXT_MIDDLE, calendar.get(Calendar.DATE)) + "");
        setDay(DateFormat.format(DEFAULT_FORMAT_TEXT_BOTTOM, calendar.get(Calendar.DATE)) + "");
        setMonth(DateFormat.format(DEFAULT_FORMAT_TEXT_TOP, calendar.get(Calendar.MONTH)) + "");*/

        setDate(calendar.get(Calendar.DATE) + "");
        setDay(calendar.get(Calendar.DAY_OF_WEEK) + "");
        setMonth(calendar.get(Calendar.MONTH + 1) + "");
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
