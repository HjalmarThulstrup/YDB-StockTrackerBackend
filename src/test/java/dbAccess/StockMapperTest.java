/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbAccess;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Wicktus
 */
public class StockMapperTest
{
    
    public StockMapperTest()
    {
    }

    /**
     * Tests that the addStockToFavourites method, of class StockMapper, is functional.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddStockToFavourites() throws Exception
    {
        System.out.println("Testing addStockToFavourites method of class StockMapper");
        int userId = 0;
        String stockSymbol = "AMD";
        StockMapper instance = new StockMapper();
        boolean result = instance.addStockToFavourites(userId, stockSymbol);
        assertTrue(result);
    }
    
}
