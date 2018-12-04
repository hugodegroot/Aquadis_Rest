package com.aquadis.models;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Lorenzo, Jan-Willem
 */
public class LapTimeTest {

    private LapTime time;

    @Before
    public void setUp() {
        time = new LapTime();
    }

    @Test
    public void setTimeWithHoursReturnCorrectFormat() throws IllegalArgumentException {
        // Correct format is: hh:mm:ss.ms
        time.setTime(0, 1, 34, 654);

        String expected = "01:34.654";
        String result = time.getTime();

        assertThat("Hours are not in the right format!", result, is(expected));
    }

    @Test
    public void setTimeWithZeroHoursReturnCorrectFormat() throws IllegalArgumentException {
        // Correct format is: hh:mm:ss.ms
        time.setTime(0, 12, 34, 654);

        String expected = "12:34.654";
        String result = time.getTime();

        assertThat("Hours should not be shown", result, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setTimeWithZeroHoursAndZeroToNineMinutesReturnCorrectFormat() throws IllegalArgumentException {
        // Correct format is: hh:mm:ss.ms

        // Zero minutes
        time.setTime(0, 0, 34, 654);
        String expected = "00:34.654";
        String result = time.getTime();
        assertThat(result + " is not in the right time!", result, is(expected));

        // One minute
        time.setTime(0, 1, 34, 654);
        expected = "01:34.654";
        result = time.getTime();
        assertThat(result + " is not in the right time!", result, is(expected));

        // Two minutes
        time.setTime(0, 2, 34, 654);
        expected = "02:34.654";
        result = time.getTime();
        assertThat(result + " is not in the right time!", result, is(expected));

        // Three minutes
        time.setTime(0, 3, 34, 654);
        expected = "03:34.654";
        result = time.getTime();
        assertThat(result + " is not in the right time!", result, is(expected));

        // Four minutes
        time.setTime(0, 4, 34, 654);
        expected = "04:34.654";
        result = time.getTime();
        assertThat(result + " is not in the right time!", result, is(expected));

        // five minutes
        time.setTime(0, 5, 34, 654);
        expected = "05:34.654";
        result = time.getTime();
        assertThat(result + " is not in the right time!", result, is(expected));

        // Six minutes
        time.setTime(0, 6, 34, 654);
        expected = "06:34.654";
        result = time.getTime();
        assertThat(result + " is not in the right time!", result, is(expected));

        // Seven minutes
        time.setTime(0, 7, 34, 654);
        expected = "07:34.654";
        result = time.getTime();
        assertThat(result + " is not in the right time!", result, is(expected));

        // Eight minutes
        time.setTime(0, 8, 34, 654);
        expected = "08:34.654";
        result = time.getTime();
        assertThat(result + " is not in the right time!", result, is(expected));

        // Nine minutes
        time.setTime(0, 9, 34, 654);
        expected = "09:34.654";
        result = time.getTime();
        assertThat(result + " is not in the right time!", result, is(expected));

        // Negative minutes
        time.setTime(0, -1, 34, 654);

        // Higher than 60 minutes
        time.setTime(0, 61, 34, 654);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setTimeWithZeroHoursAndZeroToNineSecondsReturnCorrectFormat() throws IllegalArgumentException {
        // Correct format is: hh:mm:ss.ms

        // Zero seconds
        time.setTime(2, 53, 0, 654);
        String expected = "02:53:00.654";
        String result = time.getTime();
        assertThat(result + " is not in the right time!", result, is(expected));

        // One second
        time.setTime(2, 53, 1, 654);
        expected = "02:53:01.654";
        result = time.getTime();
        assertThat(result + " is not in the right time!", result, is(expected));

        // Two seconds
        time.setTime(2, 53, 2, 654);
        expected = "02:53:02.654";
        result = time.getTime();
        assertThat(result + " is not in the right time!", result, is(expected));

        // Three seconds
        time.setTime(2, 53, 3, 654);
        expected = "02:53:03.654";
        result = time.getTime();
        assertThat(result + " is not in the right time!", result, is(expected));

        // Four seconds
        time.setTime(2, 53, 4, 654);
        expected = "02:53:04.654";
        result = time.getTime();
        assertThat(result + " is not in the right time!", result, is(expected));

        // Five seconds
        time.setTime(2, 53, 5, 654);
        expected = "02:53:05.654";
        result = time.getTime();
        assertThat(result + " is not in the right time!", result, is(expected));

        // Six seconds
        time.setTime(2, 53, 6, 654);
        expected = "02:53:06.654";
        result = time.getTime();
        assertThat(result + " is not in the right time!", result, is(expected));

        // Seven seconds
        time.setTime(2, 53, 7, 654);
        expected = "02:53:07.654";
        result = time.getTime();
        assertThat(result + " is not in the right time!", result, is(expected));

        // Eight seconds
        time.setTime(2, 53, 8, 654);
        expected = "02:53:08.654";
        result = time.getTime();
        assertThat(result + " is not in the right time!", result, is(expected));

        // Nine seconds
        time.setTime(2, 53, 9, 654);
        expected = "02:53:09.654";
        result = time.getTime();
        assertThat(result + " is not in the right time!", result, is(expected));

        // Negative number
        time.setTime(2, 53, -1, 654);

        // Higher than 60 seconds
        time.setTime(2, 53, 61, 654);
    }


    @Test
    public void setTimeWithAHundredsOfASecondReturnCorrectFormat() throws IllegalArgumentException {
        // Correct format is: hh:mm:ss.ms
        time.setTime(2, 53, 8, 54);

        String expected = "02:53:08.054";
        String result = time.getTime();

        assertEquals(expected, result);
    }

    @Test
    public void setTimeWithAThousandsOfASecondReturnCorrectFormat() throws IllegalArgumentException {
        // Correct format is: hh:mm:ss.ms
        time.setTime(2, 53, 8, 4);

        String expected = "02:53:08.004";
        String result = time.getTime();

        assertEquals(expected, result);
    }
}