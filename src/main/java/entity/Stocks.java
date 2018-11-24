package entity;

import java.io.Serializable;
import java.util.ArrayList;
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

@Entity
@Table(name = "Stocks")
public class Stocks implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "symbol")
    private String symbol;
    @JoinTable(name = "UserStock", joinColumns = {
        @JoinColumn(name = "fk_stockSymbol", referencedColumnName = "symbol")}, inverseJoinColumns = {
        @JoinColumn(name = "fk_user", referencedColumnName = "user_name")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> userList;

    public Stocks()
    {
    }

    public Stocks(String symbol)
    {
        this.symbol = symbol;
        userList = new ArrayList();
    }

    public String getSymbol()
    {
        return symbol;
    }

    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }

    public List<User> getUserList()
    {
        return userList;
    }

    public void setUserList(List<User> userList)
    {
        this.userList = userList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (symbol != null ? symbol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stocks)) {
            return false;
        }
        Stocks other = (Stocks) object;
        if ((this.symbol == null && other.symbol != null) || (this.symbol != null && !this.symbol.equals(other.symbol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return symbol;
    }

    public void addUserToList(User user)
    {
        this.userList.add(user);
    }

    public void removeUserFromList(User user)
    {
        this.userList.remove(user);
    }
}
