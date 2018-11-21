package dbAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Used for inserting and retrieving the user's favourited stock symbols.
 */
public class StockMapper
{

    private final DatabaseConnector connector;

    public StockMapper()
    {
        this.connector = new DatabaseConnector();
    }

    /**
     * Used to assign a stock a given user's favourites list.
     *
     * @param userId
     * @param stockSymbol
     * @return a boolean signifying a failed or successful database operation
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean addStockToFavourites(int userId, String stockSymbol) throws SQLException, ClassNotFoundException
    {
        try (Connection conn = connector.connection()) {
            String SQL = "INSERT INTO Stocks (symbol, user) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, stockSymbol);
            ps.setInt(2, userId);

            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException exception) {
            //make some exception
            return false;
        }

        return true;
    }
}
