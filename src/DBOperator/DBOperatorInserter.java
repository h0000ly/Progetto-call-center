package DBOperator;

import Domain.Operator;
import GUInterface.Exception.OperatorAlreadyUsed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class DBOperatorInserter {
    private DBOperatorReader dbR=new DBOperatorReader();

    /**
     * This method is used to add a new operator
     * @param connection
     * @param number
     * @param username
     * @param password
     */
    void insertOperator(Connection connection, String number,String username,String password) {

        try {
            System.err.println("[DBOperatorInserter] - Adding operator " + username + " to database...");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO operator VALUES (?,?,?,?);");
            ps.setString(1, number);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setBoolean(4,false);
            ps.execute();
            connection.commit();
            System.err.println("[DBOperatorInserter] - Operator " + username + " added to database.");
        } catch (SQLException e) {
            System.err.println("[DBOperatorInserter] - Exception " + e + " encountered in method insertOperator.");
        }
    }
}
