/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usertest;

import entity.Role;
import entity.User;
import entity.UserFacade;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rlumh
 */
public class UserFacadeTest {
    
    public UserFacadeTest() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @Test
    public void testVerifyUsername() {
        boolean validUsername = UserFacade.getInstance().verifyUsername("test");
        assertFalse(validUsername);
        
        validUsername = UserFacade.getInstance().verifyUsername("dawdasd");
        assertTrue(validUsername);
    }
    
    @Test
    public void testPersistUser() {
        User testUser = new User("testing", "1234");
        testUser.addRole(new Role("user"));
        
        User u = UserFacade.getInstance().persistUser(testUser);
        
        assertTrue(u.getUserName().equals("testing"));
        
    }
}
