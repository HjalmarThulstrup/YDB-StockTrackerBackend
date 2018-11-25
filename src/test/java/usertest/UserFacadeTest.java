/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usertest;

import entity.Role;
import entity.User;
import entity.UserFacade;
import java.util.List;
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
        User testUser = new User("yeeeeet", "1234");
        testUser.addRole(new Role("user"));
        
        User u = UserFacade.getInstance().persistUser(testUser);
        
        assertTrue(u.getUserName().equals("yeeeeet"));
        
        List<String> roles = u.getRolesAsStrings();
        
        assertTrue(roles.size() == 1);
        assertTrue(roles.get(0).equals("user"));
    }
}
