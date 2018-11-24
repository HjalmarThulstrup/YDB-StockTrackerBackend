/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usertest;

import entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestDBSetup
{

    private EntityManager em;

    public TestDBSetup()
    {

    }

    @Before
    public void setUp()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaputest");
        em = emf.createEntityManager();
    }

    @Test
    public void testAddUser()
    {

        System.out.println("addUser: addUserTest");
        User u = new User("test", "1234");
        System.out.println(u.toString());
        User result = null;

        try {
            em.getTransaction().begin();
            em.persist(u);

            result = em.find(User.class, "test");

            em.getTransaction().commit();

        } catch (Exception e) {
            //Skal nok kastes en custom exception her
        } finally {
            em.close();
        }

        assertNotNull(result);
        assertTrue(u.getUserName().equals("test"));
    }
}
