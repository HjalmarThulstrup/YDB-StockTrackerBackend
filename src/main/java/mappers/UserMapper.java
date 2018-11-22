package mappers;

import entity.User;
import exceptions.AuthenticationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserMapper {

    //Default EntityManagerFactory
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static final UserMapper instance = new UserMapper();
    
    private UserMapper(){}
    
    public static UserMapper getInstance(){
        return instance;
    }
    
    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
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

}
