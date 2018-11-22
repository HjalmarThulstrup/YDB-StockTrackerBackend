package mappers;

import entity.Stocks;
import entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Used for inserting and retrieving the user's favourited stock symbols.
 */
public class StockMapper
{

    private final EntityManagerFactory emf;
    private static StockMapper instance;

    private StockMapper(String pu)
    {
        emf = Persistence.createEntityManagerFactory(pu);
    }

    /**
     * Gets the current instance if it exists. Creates a new one of it doesnn't.
     *
     * @param pu
     * @return and instance of this class
     */
    public static StockMapper getInstance(String pu)
    {
        if (instance == null) {
            instance = new StockMapper(pu);
        }

        return instance;
    }

    /**
     * closes the entitymanager factory
     */
    public void closeInstance()
    {
        emf.close();
    }

    /**
     * Saves a stock to the user's favourites list
     *
     * @param user
     * @param symbol
     * @return boolean based on whether or not insert was successful
     */
    public boolean addStockToFavourites(User user, String symbol)
    {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            User dbUser = em.find(User.class, user.getUserName());
            Stocks dbStock = em.find(Stocks.class, symbol);

            if (dbStock == null) {
                System.out.println("Adding stock to list");

                dbStock = new Stocks(symbol);
            }

            dbUser.addToStockList(dbStock);
            dbStock.addUserToList(dbUser);

            em.persist(dbStock);
            em.persist(dbUser);

            em.getTransaction().commit();

        } catch (Exception e) {
            //Skal nok kastes en custom exception her

            return false;
        } finally {
            em.close();
        }

        return true;
    }
}
