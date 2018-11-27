package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
public class User implements Serializable
{

    @ManyToMany(mappedBy = "userList", fetch = FetchType.LAZY)
    private List<Stocks> stocksList;
    @ManyToMany(mappedBy = "userCollection")
    private Collection<Role> roleCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_name", length = 25)
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "user_pass")
    private String userPass;
    @JoinTable(name = "user_roles", joinColumns = {
        @JoinColumn(name = "user_name", referencedColumnName = "user_name")}, inverseJoinColumns = {
        @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
    @ManyToMany
    private List<Role> roleList = new ArrayList();

    public List<String> getRolesAsStrings()
    {
        if (roleList.isEmpty()) {
            return null;
        }
        List<String> rolesAsStrings = new ArrayList();
        for (Role role : roleList) {
            rolesAsStrings.add(role.getRoleName());
        }
        return rolesAsStrings;
    }

    public User()
    {
    }

    //TODO Change when password is hashed
    public boolean verifyPassword(String pw)
    {
        return BCrypt.checkpw(pw, userPass);
        //return(pw.equals(userPass));
    }

    public User(String userName, String userPass)
    {
        this.userName = userName;
        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(userPass, salt);
        this.userPass = hash;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPass()
    {
        return this.userPass;
    }

    public void setUserPass(String userPass)
    {
        this.userPass = userPass;
    }

    public List<Role> getRoleList()
    {
        return roleList;
    }

    public void setRoleList(List<Role> roleList)
    {
        this.roleList = roleList;
    }

    public void addRole(Role userRole)
    {
        roleList.add(userRole);
    }

    @XmlTransient
    public Collection<Role> getRoleCollection()
    {
        return roleCollection;
    }

    public void setRoleCollection(Collection<Role> roleCollection)
    {
        this.roleCollection = roleCollection;
    }

    public List<Stocks> getStocksList()
    {
        return stocksList;
    }

    public void setStocksList(List<Stocks> stocksList)
    {
        this.stocksList = stocksList;
    }

    public void addToStockList(String symbol)
    {
        this.stocksList.add(new Stocks(symbol));
    }

    public void addToStockList(Stocks stock)
    {
        this.stocksList.add(stock);
    }

    public void removeFromStockList(Stocks stock)
    {
        this.stocksList.remove(stock);
    }

    public void removeFromStockList(String symbol)
    {
        this.stocksList.remove(new Stocks(symbol));
    }

    public List<String> getStocksListAsStrings()
    {
        List<String> result = new ArrayList();
        for (Stocks stock : stocksList) {
            result.add(stock.toString());
        }

        return result;
    }
}

/*
=======
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "user_name", length = 25)
  private String userName;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "user_pass")
  private String userPass;
  @JoinTable(name = "user_roles", joinColumns = {
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")}, inverseJoinColumns = {
    @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
  @ManyToMany
  private List<Role> roleList = new ArrayList();
  

  @Basic(optional = true)
  @ElementCollection
  @CollectionTable(
        name="user_symbol",
        joinColumns=@JoinColumn(name="user"))
  @Column(name = "symbol")
  private List<String> symbolList = new ArrayList<>();

  public List<String> getRolesAsStrings() {
    if (roleList.isEmpty()) {
      return null;
    }
    List<String> rolesAsStrings = new ArrayList();
    for (Role role : roleList) {
      rolesAsStrings.add(role.getRoleName());
    }
    return rolesAsStrings;
  }

  public User() {}

   public boolean verifyPassword(String pw){
        return BCrypt.checkpw(pw, userPass);
        //return(pw.equals(userPass));
    }

  public User(String userName, String userPass) {
    this.userName = userName;
    String salt = BCrypt.gensalt();
    String hash = BCrypt.hashpw(userPass, salt);
    this.userPass = hash;
  }
  
  public void addSymbol(String s) {
      this.symbolList.add(s);
  }
  
  public List<String> getSymbols() {
      if(this.symbolList.isEmpty()) {
          return null;
      }
      return this.symbolList;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPass() {
    return this.userPass;
  }

  public void setUserPass(String userPass) {
    this.userPass = userPass;
  }

  public List<Role> getRoleList() {
    return roleList;
  }

  public void setRoleList(List<Role> roleList) {
    this.roleList = roleList;
  }

  public void addRole(Role userRole) {
    roleList.add(userRole);
  }

}
>>>>>>> createUserFeature
*/
