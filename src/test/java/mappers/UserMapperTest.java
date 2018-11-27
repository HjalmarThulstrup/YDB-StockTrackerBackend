package mappers;

import entity.Role;
import entity.Stocks;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testUtils.Order;
import testUtils.OrderedRunner;

@RunWith(OrderedRunner.class)
public class UserMapperTest
{
    EntityManagerFactory emf;
    public UserMapperTest()
    {
    }
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("jpaputest");
        
    }

    /**
     * Test of getUserStockList method, of class UserMapper.
     */
    @Test
   
    public void testGetUserStockList()
    {
        //Create EntityManager to connect to db
        EntityManager em = emf.createEntityManager();
        
        //Create user, add role and stocks to put into db
        User u = new User("TestStockListUserYEET", "1234");
        u.addRole(em.find(Role.class, "user"));
        
        
        //Persist and commit user to db
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        em.close();
        StockMapper.getInstance("jpaputest").addStockToFavourites(u, "TST");
        
        System.out.println("Testing getUserStockList");
        String username = "TestStockListUserYEET";

        UserMapper instance = UserMapper.getInstance("jpaputest");

        List<Stocks> expResult = new ArrayList()
        {
            {
                add(new Stocks("TST"));
            }
        };

        List<Stocks> result = instance.getUserStockList(username);
        assertEquals(expResult, result);
    }

}
