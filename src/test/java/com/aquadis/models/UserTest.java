package com.aquadis.models;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Lorenzo, Jan-Willem
 */
public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User("lkorn.9520@gmail.com", "Lorenzo", "Korn", "lorenzo123", 1); // Arrange
    }

    @Test
    public void getEmailReturnEmail() {
        String expected = "lkorn.9520@gmail.com";
        String result = user.getEmail();

        assertEquals(expected, result);
    }

    @Test
    public void getEmailReturnEmailNotNull() {
        String result = user.getEmail();

        assertNotNull(result);
    }

    @Test
    public void getFirstNameReturnFirstName() {
        String expected = "Lorenzo";
        String result = user.getFirstName();

        assertEquals(expected, result);
    }

    @Test
    public void getFirstNameReturnFirstNameNotNull() {
        String result = user.getFirstName();

        assertNotNull(result);
    }

    @Test
    public void getLastName() {
        String expected = "Korn";
        String result = user.getLastName();

        assertEquals(expected, result);
    }

    @Test
    public void getLastNameReturnLastNameNotNull(){
        String result = user.getFirstName();

        assertNotNull(result);
    }

    @Test
    public void getPasswordReturnPassword() {
        String expected = "lorenzo123";
        String result = user.getPassword();

        assertEquals(expected, result);
    }

    @Test
    public void getPasswordReturnPasswordNotNull() {
        String expected = "lorenzo123";
        String result = user.getPassword();

        assertEquals(expected, result);
    }

    @Test
    public void getAdminStatusReturnStatus() {
        int expected = 1;
        int result = user.getAdminStatus();

        assertEquals(expected, result);
    }

    @Test
    @Ignore
    public void getGroups() {
    }

    @Test
    @Ignore
    public void setGroups() {
    }
}