package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import exceptions.AuthenticationException;

public class UserFacade {

    //Default EntityManagerFactory
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaputest");
    private static final UserFacade instance = new UserFacade();
    
    private UserFacade(){}
    
    public static UserFacade getInstance(){
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
    
    public boolean verifyUsername(String username) {
        EntityManager em = emf.createEntityManager();
        User u = em.find(User.class, username);
        
        return u == null;
         
   }

    public User persistUser(User u) {
        
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            em.close();
            
            return u;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        
    }
    

}
