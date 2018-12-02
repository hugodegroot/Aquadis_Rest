package com.aquadis.models;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LapTimeTest {

    private LapTime time;

    @Before
    public void setUp() {
        time = new LapTime();
    }

    @Test
    public void setTimeWithHoursReturnCorrectFormat() {
        // Correct format is: h:m:s.ms
        time.setTime(0,1, 34, 654);

        String expected = "01:34.654";
        String result = time.getTime();

        assertThat("Not the right format!", result, is(expected));
    }

    @Test
    public void setTimeWithZeroHoursReturnCorrectFormat() {
        // Correct format is: m:s.ms
        time.setTime(2,12, 34, 654);

        String expected = "02:12:34.654";
        String result = time.getTime();

        assertEquals(expected, result);
    }

    @Test
    public void setTimeWithZeroHoursAndZeroToNineMinutesReturnCorrectFormat() {
        // Correct format is: m:s.ms
        time.setTime(2,1, 34, 654);

        String expected = "02:01:34.654";
        String result = time.getTime();

        assertEquals(expected, result);
    }

    @Test
    public void setTimeWithZeroHoursAndZeroToNineSecondsReturnCorrectFormat() {
        // Correct format is: m:s.ms
        time.setTime(2,53, 8, 654);

        String expected = "02:53:08.654";
        String result = time.getTime();

        assertEquals(expected, result);
    }
}