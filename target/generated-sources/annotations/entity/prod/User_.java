package entity.prod;

import entity.Role;
import entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-24T14:53:40")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> userPass;
    public static volatile SingularAttribute<User, String> userName;
    public static volatile ListAttribute<User, Role> roleList;
    public static volatile ListAttribute<User, String> symbolList;

}