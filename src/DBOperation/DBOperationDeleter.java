package DBOperation;


import dataHistory.DataWriterServer;
import model.Operation;
import GUInterface.Exception.DBOperationEmpty;
import GUInterface.Exception.OperationNotFound;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DBOperationDeleter {

    /**
     * This method is called to remove an operation in the database
     * @param connection
     * @param numCalling
     * @param id
     * @param number
     */
    void removeOperation(Connection connection, String numCalling, String id, String number) {
        boolean found = false;
        DataWriterServer data = new DataWriterServer(numCalling);
        DBOperationReader operationReader = new DBOperationReader();
        if (operationReader.retrieveAllTheOperations(connection, number).isEmpty()) {
            DBOperationEmpty operationEmpty = new DBOperationEmpty();
            operationEmpty .setVisible(true);
        }
		else {
            for (Operation operation : operationReader.retrieveAllTheOperations(connection, number)) {
                if (operation.equalsIDString(id, number)) {
                    found = true;
					break;
                }
            }
            if (found) {
                try {
                    System.err.println("[DBOperationDeleter] - Removing operation in  db " + id + " number " + number + "...");
                    PreparedStatement ps = connection.prepareStatement("DELETE FROM operation WHERE id = ? AND numbe = ?;");
                    ps.setString(1, id);
                    ps.setString(2, number);
                    ps.execute();
                    connection.commit();
                    System.err.println("[DBOperationDeleter] - Operation " + id + " number " + number + " removed.");
                    data.updateHistory("Operation " + id + " at number " + number + " removed.");
                } catch (SQLException e) {
                    System.err.println("[DBOperationDeleter] - Exception " + e + " encountered in method removeOperation.");
                }
            } else {
                OperationNotFound operationNotFound = new OperationNotFound();
                operationNotFound.setVisible(true);
            }
        }
    }
	
}


