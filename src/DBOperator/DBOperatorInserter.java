package DBOperator;

import dataHistory.DataWriter;
import dataHistory.DataWriterServer;
import model.Operator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class DBOperatorInserter {
    private DataWriterServer data;

    /**
     * This method is used to add a new operator in the database
     * @param connection
     * @param numCalling
     * @param operator
     */
    void insertOperator(Connection connection,String numCalling, Operator operator) {
        data=new DataWriterServer(numCalling);
        try {
            System.err.println("[DBOperatorInserter] - Adding operator " + operator.getUsername() + " to database...");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO operator VALUES (?,?,?,?);");
            ps.setString(1, operator.getNumber());
            ps.setString(2, operator.getUsername());
            ps.setString(3, operator.getPassword());
            ps.setBoolean(4,false);
            ps.execute();
            connection.commit();
            System.err.println("[DBOperatorInserter] - Operator " + operator.getUsername() + " added to database.");
            data.updateHistory("Operator " + operator.getUsername() + "at number "+operator.getNumber()+" added to database.");
        } catch (SQLException e) {
            System.err.println("[DBOperatorInserter] - Exception " + e + " encountered in method insertOperator.");
        }
    }
}
