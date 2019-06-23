package DBOperation;

import dataHistory.DataWriter;
import dataHistory.DataWriterServer;
import model.Operation;
import GUInterface.Exception.OperationAlreadyUsed;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBOperationInserter {

    /**
     * This method is called to add a new operation in the database
     * @param connection
     * @param numCalling
     * @param operation
     */
    public void addOperation(Connection connection,String numCalling,Operation operation){
        DataWriterServer data  =new DataWriterServer(numCalling);
        DBOperationReader operationReader = new DBOperationReader();
        boolean found = false;
        for (Operation operation : operationReader.retrieveAllTheOperations(connection, operation.getNumber())) {
            if(operation.equals(operation)) {
                found = true;
				break;
            }
        }
        if(!found) {
            try {
                System.err.println("[DBOperationInserter] - Adding operation " + operation.getId() + " to database...");
                PreparedStatement ps = connection.prepareStatement("INSERT INTO operation VALUES (?,?,?);");
                ps.setString(1, operation.getId());
                ps.setString(2, operation.getNumber());
                ps.setString(3, operation.getTextOp());
                ps.execute();
                connection.commit();
                System.err.println("[DBOperationInserter] - Operation " + operation.getId() + " added to database.");
                data.updateHistory("Operation "+ operation.getId() + " at number" + operation.getNumber() + " added to Database");
            } catch (SQLException e) {
                System.err.println("[DBOperationInserter] - Exception " + e + " encountered in method insertOperation.");
            }
        }
        else{
            OperationAlreadyUsed operationAlreadyUsed = new OperationAlreadyUsed();
            operationAlreadyUsed.setVisible(true);
        }
    }

}

