package DBOperator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManagerOperator {

    private final String DATABASE="operatordb";
    private final String USERNAME="root";
    private final String PASSWORD="ASSAssincreed5";

    /**
     * This method is used to connect to the database of the operators
     * @param connection
     * @return
     */
    Connection connectToDB(Connection connection) {
        try {
            System.err.println("[DBConnectionManagerOperator] - Connecting to database " + DATABASE + "...");
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE + "?serverTimezone=UTC" , USERNAME, PASSWORD);
            connection.setAutoCommit(false);
            System.err.println("[DBConnectionManagerOperator] - Connected.");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("[DBConnectionManagerOperator] - Exception " + e + " encountered in method connectToDB.");
        }
        return connection;
    }

    /**
     * This method is used to disconnect from the database of the operators
     */
    Connection disconnectFromDB(Connection connection) {
        try {
            System.err.println("[DBConnectionManagerOperator] - Disconnecting from database " + DATABASE + "...");
            connection.close();
            System.err.println("[DBConnectionManagerOperator] - Disconnected.");
        } catch (SQLException e) {
            System.err.println("[DBConnectionManagerOperator] - Exception " + e + " encountered in method disconnectFromDB.");
        }
        return connection;
    }
}
