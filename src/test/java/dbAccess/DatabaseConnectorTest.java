/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbAccess;

import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Wicktus
 */
public class DatabaseConnectorTest
{
    
    public DatabaseConnectorTest()
    {
    }

    /**
     * Test of connection method, of class DatabaseConnector.
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testConnectionCreator() throws SQLException, ClassNotFoundException
    {
        System.out.println("Testing connection method of class DatabaseConnector");
        DatabaseConnector instance = new DatabaseConnector();
        
        try (Connection conn = instance.connection()) {
            System.out.println(conn.getClientInfo());
            Boolean expResult = conn.isValid(15);
            assertTrue(expResult);
        }
        
        //fail("Test failed due to invalid connection");
    }
    
}
