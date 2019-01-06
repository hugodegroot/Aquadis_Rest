package com.aquadis.rest.resources;

import com.aquadis.models.User;
import com.aquadis.service.RepositoryService;
import com.aquadis.service.impl.RepositoryServiceImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * @author Lorenzo, Jan-Willen
 */
@Ignore
public class UserResourceTest {

    private static RepositoryService service;

    @BeforeClass
    public static void setUp() {
        service = RepositoryServiceImpl.getInstance();
    }

    @Test
    public void getAllUsersReturnsAllUsers() {
        int numberOfUsers = 4;
        List<User> users = service.getAllUsers();
        System.out.println("WTF WRM DOET DIT DING T NIT");
        Assert.assertThat("Not all the user are gotten", numberOfUsers, is(users.size()));
    }
}