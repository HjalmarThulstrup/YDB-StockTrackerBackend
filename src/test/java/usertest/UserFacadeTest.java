/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usertest;

import entity.Role;
import entity.User;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mappers.UserMapper;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rlumh
 */
public class UserFacadeTest {
    EntityManagerFactory emf;
    
    public UserFacadeTest() {
    }
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("jpaputest");
        
    }
    
    @Test
    public void testVerifyUsername() {
        EntityManager em = emf.createEntityManager();
        User testUser = new User("yoat", "1234");
        testUser.addRole(em.find(Role.class, "user"));
        
        User u = UserMapper.getInstance("jpaputest").persistUser(testUser);
        
        boolean validUsername = UserMapper.getInstance("jpaputest").verifyUsername("yoat");
        assertFalse(validUsername);
        
        validUsername = UserMapper.getInstance("jpaputest").verifyUsername("validUsername");
        assertTrue(validUsername);
    }
    
    @Test
    public void testPersistUser() {
        EntityManager em = emf.createEntityManager();
        
        
        em = emf.createEntityManager();
        
        User testUser = new User("yeeet", "1234");
        testUser.addRole(em.find(Role.class, "user"));
        
        User u = UserMapper.getInstance("jpaputest").persistUser(testUser);
        
        assertTrue(u.getUserName().equals("yeeet"));
        
        List<String> roles = u.getRolesAsStrings();
        
        assertTrue(roles.size() == 1);
        assertTrue(roles.get(0).equals("user"));
    }
}
