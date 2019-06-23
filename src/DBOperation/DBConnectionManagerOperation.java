package DBOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManagerOperation {

    private final String DATABASE = "operationdb";
    private final String USERNAME = "root";
    private final String PASSWORD = "ASSAssincreed5";

    /**
     * Connect to database of the operations
     * @param connection
     * @return connection
     */
    Connection connectToDB(Connection connection) {
        try {
            System.err.println("[DBConnectionManagerOperation] - Connecting to database " + DATABASE + "...");
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE+"?serverTimezone=UTC" , USERNAME, PASSWORD );
            connection.setAutoCommit(false);
            System.err.println("[DBConnectionManagerOperation] - Connected.");
        } catch (SQLException e) {
            System.err.println("[DBConnectionManagerOperation] - Exception " + e + " encountered in method connectToDB.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Disconnect from the database of the operations
     * @param connection
     * @return connection
     */
    Connection disconnectFromDB(Connection connection) {
        try {
            System.err.println("[DBConnectionManagerOperation] - Disconnecting from database " + DATABASE + "...");
            connection.close();
            System.err.println("[DBConnectionManagerOperation] - Disconnected.");
        } catch (SQLException e) {
            System.err.println("[DBConnectionManagerOperation] - Exception " + e + " encountered in method disconnectFromDB.");
        }
        return connection;
    }

}
