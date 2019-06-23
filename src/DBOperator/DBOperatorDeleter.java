package DBOperator;

import dataHistory.DataWriter;
import dataHistory.DataWriterServer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBOperatorDeleter {

    /**
     * This method is used to remove a defined operator in the database
     * @param connection
     * @param numCalling
     * @param number
     * @param username
     */
    void removeOperator(Connection connection, String numCalling,String number,String username) {
        DataWriterServer dataWriter = new DataWriterServer(numCalling);
		try {
			System.err.println("[DBOperatorDeleter] - Removing operator in  db" + username + " number " + number + "...");
			PreparedStatement ps = connection.prepareStatement("DELETE FROM operator WHERE username = ? AND numbe = ?;");
			ps.setString(1, username);
			ps.setString(2, number);
			ps.execute();
			connection.commit();
			System.err.println("[DBOperatorDeleter] - Operator " + username + " number " + number + " removed.");
			dataWriter.updateHistory("Operator " + username + " at number " + number + " removed.");
		} catch (SQLException e) {
			System.err.println("[DBOperatorDeleter] - Exception " + e + " encountered in method removeOperator.");
		}
    }
	
}
