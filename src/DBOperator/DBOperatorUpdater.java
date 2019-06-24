package DBOperator;


import dataHistory.DataWriterServer;
import model.Operator;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBOperatorUpdater {

    /**
     * This method is used to change the username of an operator in the database
     * @param connection
     * @param numCalling
     * @param number
     * @param oldUsername
     * @param newUsername
     */
    public void updateUsername(Connection connection,String numCalling, String number,String oldUsername,String newUsername) {
        DataWriterServer dataWriter = new DataWriterServer(numCalling);
		try {
			System.err.println("[DBOperatorUpdater] - Updating username " + oldUsername + "...");
			PreparedStatement ps = connection.prepareStatement("UPDATE operator SET username= ? WHERE username = ? AND numbe= ?;");
			ps.setString(1, newUsername);
			ps.setString(2, oldUsername);
			ps.setString(3, number);
			ps.execute();
			connection.commit();
			System.err.println("[DBOperatorUpdater] - Username " + newUsername + " updated.");
			dataWriter.updateHistory("Username updated, new username: " + newUsername);
		} catch (SQLException e) {
			System.err.println("[DBOperatorUpdater] - Exception " + e + " encounterd in method updateUsername.");
		}
    }

    /**
     * This method is used to change the password of an operator in the database
     * @param connection
     * @param numCalling
     * @param operator
     */
    void updatePassword(Connection connection,String numCalling,Operator operator){
        DataWriterServer dataWriter = new DataWriterServer(numCalling);
		try {
			System.err.println("[DBOperatorUpdater] - Updating password for " + operator.getUsername() + "...");
			PreparedStatement ps = connection.prepareStatement("UPDATE operator SET passwor=? WHERE username = ? AND numbe=?;");
			ps.setString(1, operator.getPassword());
			ps.setString(2, operator.getUsername());
			ps.setString(3, operator.getNumber());
			ps.execute();
			connection.commit();
			System.err.println("[DBOperatorUpdater] - Password " + operator.getPassword() + " updated.");
			dataWriter.updateHistory("Password updated for the operator " + operator.getUsername() + " and number " + operator.getNumber());
		} catch (SQLException e) {
			System.err.println("[DBOperatorUpdater] - Exception " + e + " encountered in method updatePassword.");
		}
    }

    /**
     * This method change the status (logged in or logged out) of an operator in the database
     * @param connection
     * @param numCalling
     * @param operator
     */
    void logged(Connection connection, String numCalling,Operator operator){
    	DataWriterServer dataWriter=new DataWriterServer(numCalling);
        try {
			System.err.println("[DBOperatorUpdater] - Updating status of the operator" + operator.getUsername() + "...");
			PreparedStatement ps = connection.prepareStatement("UPDATE operator SET LoggedIn =? WHERE username = ? AND numbe=?;");
			ps.setBoolean(1, operator.isLoggedIn());
			ps.setString(2, operator.getUsername());
			ps.setString(3,operator.getNumber());
			ps.execute();
			connection.commit();
			System.err.println("[DBOperatorUpdater] - Status for operator " + operator.getUsername() + " changed to " + operator.isLoggedIn());
			dataWriter.updateHistory("Status for operator "+operator.getUsername()+ "changed to "+operator.isLoggedIn());
			System.err.println(operator.isLoggedIn()? "LOGGED IN" : "LOGGED OUT");
			dataWriter.updateHistory(operator.isLoggedIn()? "LOGGED IN":"LOGGED OUT");
		} catch (SQLException e) {
			System.err.println("[DBOperatorUpdater] - Exception " + e + " encountered in method logged.");
		}
	}

}
