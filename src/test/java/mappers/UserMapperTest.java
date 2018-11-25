package mappers;

import entity.Stocks;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import testUtils.Order;
import testUtils.OrderedRunner;

@RunWith(OrderedRunner.class)
public class UserMapperTest
{

    public UserMapperTest()
    {
    }

    /**
     * Test of getUserStockList method, of class UserMapper.
     */
    @Test
    @Order(order = 2)
    public void testGetUserStockList()
    {
        System.out.println("Testing getUserStockList");
        String username = "TestStockListUser";

        UserMapper instance = UserMapper.getInstance("jpaputest");

        List<Stocks> expResult = new ArrayList()
        {
            {
                add(new Stocks("AMD"));
            }
        };

        List<Stocks> result = instance.getUserStockList(username);
        assertEquals(expResult, result);
    }

}
