package com.aquadis.models;

public class LapTime {

    private StringBuilder time;

    public String getTime() {
        return time.toString();
    }

    public void setTime(int hours, int minutes, int seconds, int milliseconds) {
        time = new StringBuilder();

        if (hours > 0) {
            time.append(hours)
                    .append(":");
        }

        time.append(minutes)
                .append(":")
                .append(seconds)
                .append(".")
                .append(milliseconds);
    }
}
