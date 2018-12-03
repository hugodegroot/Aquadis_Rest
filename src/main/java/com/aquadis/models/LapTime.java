package com.aquadis.models;

public class LapTime {

    private final int FILL_NUMBER = 0;
    private final int MIN_HR_M_S = 0;
    private final int MIN_MS = 0;
    private final int MIN_NUMBER = 10;
    private final int MAX_HR_M_S = 60;
    private final int MIN_MILLISECONDS = 100;
    private final int MAX_MS = 1000;
    private StringBuilder time;

    public String getTime() {
        return time.toString();
    }

    public void setTime(int hours, int minutes, int seconds, int milliseconds) throws IllegalArgumentException {
        time = new StringBuilder();

        if (hours < MIN_HR_M_S || hours >= MAX_HR_M_S ||
                minutes < MIN_HR_M_S || minutes >= MAX_HR_M_S ||
                seconds < MIN_HR_M_S || seconds >= MAX_HR_M_S ||
                milliseconds < MIN_MS || milliseconds >= MAX_MS) {
            throw new IllegalArgumentException();
        }

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

    private void formatSmallNumbers(StringBuilder stringBuilder, int time) {
        if (time < MIN_NUMBER) {
            stringBuilder.append(FILL_NUMBER);
        }
        stringBuilder.append(time);
    }
}
