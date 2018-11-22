package entity.dev;

import entity.Role;
import entity.Stocks;
import entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-22T19:34:26")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile ListAttribute<User, Stocks> stocksList;
    public static volatile CollectionAttribute<User, Role> roleCollection;
    public static volatile SingularAttribute<User, String> userPass;
    public static volatile SingularAttribute<User, String> userName;
    public static volatile ListAttribute<User, Role> roleList;

}