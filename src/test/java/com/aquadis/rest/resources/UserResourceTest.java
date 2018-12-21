package com.aquadis.rest.resources;

import com.aquadis.models.User;
import com.aquadis.service.RepositoryService;
import com.aquadis.service.impl.RepositoryServiceImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * @author Lorenzo, Jan-Willen
 */
@Ignore
public class UserResourceTest {

    @BeforeClass
    public static void setUp() {
    }

    @Test
    @Ignore
    public void getAllUsersReturnsAllUsers() {
        int numberOfUsers = 4;
//        List<User> users = userResource.getAllUsers();

//        Assert.assertThat("Not all the user are gotten", numberOfUsers, is(users.size()));
    }
}