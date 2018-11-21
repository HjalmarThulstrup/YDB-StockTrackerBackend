package dbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DatabaseConnector class is used to create and keep track of database connections.
 */
public class DatabaseConnector {

    private static final String URL = "jdbc:mysql://"; //add proper server
    private static final String USERNAME = ""; //user proper user
    private static final String PASSWORD = ""; //user proper password

    private static Connection connection;

    /**
     * Checks if a database connection exists and creates one if it doesn't.
     * @return a database connection object
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public Connection connection() throws ClassNotFoundException, SQLException {
        if ( connection == null ) {
            Class.forName( "com.mysql.jdbc.Driver" );
            connection = DriverManager.getConnection( URL, USERNAME, PASSWORD );
        }
        return connection;
    }

}
