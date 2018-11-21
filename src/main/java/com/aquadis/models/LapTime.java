package com.aquadis.models;

public class LapTime {

    private StringBuilder time;

    public String getTime() {
        return time.toString();
    }

    public void setTime(int minutes, int seconds, int milliseconds) {
        time = new StringBuilder();
        time.append(minutes)
                .append(":")
                .append(seconds)
                .append(".")
                .append(milliseconds);
    }

    public void setTime(int hours, int minutes, int seconds, int milliseconds) {
        time = new StringBuilder();
        time.append(hours)
                .append(":")
                .append(minutes)
                .append(":")
                .append(seconds)
                .append(".")
                .append(milliseconds);
    }
}
