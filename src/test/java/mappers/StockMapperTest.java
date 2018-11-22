package mappers;

import entity.User;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class StockMapperTest
{

    public StockMapperTest()
    {
    }

    /**
     * Tests that addStockToFavourites functions as expected.
     */
    @Test
    public void testAddStockToFavourites()
    {
        System.out.println("Testing addStockToFavourites");
        
        User user = new User("test2", "1234");
        String symbol = "GOOG";
        StockMapper instance = StockMapper.getInstance("pu");
        
        boolean result = instance.addStockToFavourites(user, symbol);
        instance.closeInstance();
        
        assertTrue(result);
    }

}
