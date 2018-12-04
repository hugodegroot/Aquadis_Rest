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

    private RepositoryService repositoryService;

    @BeforeClass
    public void setUp() {
        EntityManagerFactory entityManagerFactory;
        EntityManager entityManager;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("aquadisPU");
            entityManager = entityManagerFactory.createEntityManager();
            setUpDatabase(entityManager);
        } catch (Exception e) {
            e.printStackTrace();
        }

        repositoryService = RepositoryServiceImpl.getInstance();
    }

    private void setUpDatabase(EntityManager entityManager) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            // adds Luuk to the database
            User user = new User("luuk123@hotmail.nl", "Luuk", "Goedhart", "luuk123", 1);
            entityManager.persist(user);
            transaction.commit();

            // adds Lorenzo to the database
            user = new User("lkorn.9520@gmail.com", "Lorenzo", "Korn", "lorenzo123", 1);
            entityManager.persist(user);
            transaction.commit();

            // adds Jan-Willem to the database
            user = new User("jwvbremen@hotmail.nl", "Jan-Willem", "van Bremen", "jw123", 0);
            entityManager.persist(user);
            transaction.commit();

            // adds Hugo to the database
            user = new User("hugo123@outlook.nl", "Hugo", "de Groot", "hugo123", 0);
            entityManager.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Test
    @Ignore
    public void getAllUsersReturnsAllUsers() {
        int numberOfUsers = 4;
        List<User> users = repositoryService.getAllUsers();

        Assert.assertThat("Not all the user are gotten", numberOfUsers, is(users.size()));
    }
}