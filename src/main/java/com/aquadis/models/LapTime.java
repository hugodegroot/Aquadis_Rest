package com.aquadis.models;

public class LapTime {

    private final int FILL_NUMBER = 0;
    private final int MIN_NUMBER = 10;
    private final int MIN_MILLISECONDS = 100;
    private StringBuilder time;

    public String getTime() {
        return time.toString();
    }

    public void setTime(int hours, int minutes, int seconds, int milliseconds) {
        time = new StringBuilder();


        if (hours > FILL_NUMBER) {
            formatSmallNumbers(time, hours);
            time.append(":");
        }

        formatSmallNumbers(time, minutes);
        time.append(":");
        formatSmallNumbers(time, seconds);
        time.append(".");

        if (milliseconds < MIN_NUMBER) {
            time.append(FILL_NUMBER).append(FILL_NUMBER);
        } else if (milliseconds < MIN_MILLISECONDS) {
            time.append(FILL_NUMBER);
        }
        time.append(milliseconds);
    }

    private void formatSmallNumbers(StringBuilder stringBuilder, int time){
        if (time < MIN_NUMBER){
            stringBuilder.append(FILL_NUMBER);
        }
        stringBuilder.append(time);
    }
}
