package mappers;

import entity.Stocks;
import entity.User;
import exceptions.AuthenticationException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * UserMapper is used when interacting with the Database in a user object
 * context
 */
public class UserMapper
{

    private final EntityManagerFactory emf;
    private static UserMapper instance;

    private UserMapper(String pu)
    {
        emf = Persistence.createEntityManagerFactory(pu);
    }

    /**
     * Gets the current instance if it exists. Creates a new one of it doesnn't.
     *
     * @param pu
     * @return and instance of this class
     */
    public static UserMapper getInstance(String pu)
    {
        if (instance == null) {
            instance = new UserMapper(pu);
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
     * Verifies that the username and password is correct.
     *
     * @param username
     * @param password
     * @return
     * @throws AuthenticationException
     */
    public User getVerifiedUser(String username, String password) throws AuthenticationException
    {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    /**
     * Gets the given user's list of favourite stocks.
     *
     * @param username
     * @return a list of stocks.
     */
    public List<Stocks> getUserStockList(String username)
    {
        EntityManager em = emf.createEntityManager();

        User user = em.find(User.class, username);
        em.close();

        return user.getStocksList();
    }

}
