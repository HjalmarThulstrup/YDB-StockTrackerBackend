package mappers;

import entity.User;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import testUtils.Order;
import testUtils.OrderedRunner;

@RunWith(OrderedRunner.class)
public class StockMapperTest
{

    public StockMapperTest()
    {
    }

    /**
     * Tests that addStockToFavourites functions as expected.
     */
    @Test
    @Order(order = 1)
    public void testAddStockToFavourites()
    {
        System.out.println("Testing addStockToFavourites");

        User user = new User("test", "1234");

        String symbol = "GOOG";
        StockMapper instance = StockMapper.getInstance("jpaputest");
                
        boolean result = instance.addStockToFavourites(user, symbol);
        assertTrue(result);
    }

    /**
     * Test of removeStockFromFavourites method, of class StockMapper.
     */
    @Test
    @Order(order = 2)
    public void testRemoveStockFromFavourites()
    {
        System.out.println("Testing removeStockFromFavourites");
        User user = new User("test", "1234");
        String symbol = "GOOG";
        StockMapper instance = StockMapper.getInstance("jpaputest");

        boolean result = instance.removeStockFromFavourites(user, symbol);
        assertTrue(result);
    }

}
