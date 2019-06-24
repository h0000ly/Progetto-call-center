package DBOperation;

import GUInterface.Exception.OperationAlreadyUsed;

import dataHistory.DataWriterServer;
import model.Operation;
import GUInterface.Exception.DBOperationEmpty;
import GUInterface.Exception.OperationNotFound;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBOperationUpdater {
	
    DBOperationReader operationReader = new DBOperationReader();

    /**
     * This method is called to change the id of an operation in the dataWriterbase
     * @param connection
     * @param numCalling
     * @param oldId
     * @param number
     * @param newId
     */
    void changeOperationID(Connection connection, String numCalling, String oldId, String number, String newId) {
        boolean found = false;
        boolean updatedFound = false;
        DataWriterServer dataWriter = new DataWriterServer(numCalling);
        if(operationReader.retrieveAllTheOperations(connection, number).isEmpty()){
            DBOperationEmpty operationEmpty = new DBOperationEmpty();
            operationEmpty.setVisible(true);
        }
        else {
            for (Operation operation : operationReader.retrieveAllTheOperations(connection, number)) {
                if (operation.equalsIDString(oldId, number)) {
                    found = true;
                }
                if(operation.equalsIDString(newId,number)) {
                    updatedFound=true;
                }
            }
            if (found && !updatedFound) {
                try {
                    System.err.println("[DBOperationUpdater] - Updating id " + oldId);
                    PreparedStatement ps = connection.prepareStatement("UPDATE operation SET id = ? WHERE id = ? AND numbe = ?;");
                    ps.setString(1, newId);
                    ps.setString(2, oldId);
                    ps.setString(3, number);
                    ps.execute();
                    connection.commit();
                    System.err.println("[DBOperationUpdater] - id " + oldId + " changed in " + newId );
                    dataWriter.updateHistory("Id apdated for the operation " + oldId + " at number " + number + ", new id: " + newId);
                } catch (SQLException e) {
                    System.err.println("[DBOperationUpdater] - Exception " + e + " encountered in method changeOperationID.");
                }
            } else {
                if (!found) {
                    OperationNotFound operationNotFound = new OperationNotFound();
                    operationNotFound.setVisible(true);
                }
                else if( updatedFound) {
                    OperationAlreadyUsed operationAlreadyUsed = new OperationAlreadyUsed();
                    operationAlreadyUsed.setVisible(true);
                }
            }
        }
    }

    /**
     * This method is used to change the text of an operation in the dataWriterbase
     * @param connection
     * @param numCalling
     * @param operation
     */
    void changeOperationText(Connection connection, String numCalling, Operation operation) {
        boolean found = false;
        DataWriterServer dataWriter = new DataWriterServer(numCalling);
        if (operationReader.retrieveAllTheOperations(connection, operation.getNumber()).isEmpty()) {
            DBOperationEmpty operationEmpty = new DBOperationEmpty();
            operationEmpty.setVisible(true);
        }
        else {
            for (Operation operationAlt : operationReader.retrieveAllTheOperations(connection, operation.getNumber())) {
                if (operationAlt.equalsIDString(operation.getId(), operation.getNumber())) {
                    found = true;
                }
            }
            if (found) {
                try {
                    System.err.println("[DBOperationUpdater] - Updating text at " + operation.getId() );
                    PreparedStatement ps = connection.prepareStatement("UPDATE operation SET tex= ? WHERE id = ? AND numbe= ?;");
                    ps.setString(1, operation.getTextOp());
                    ps.setString(2, operation.getId());
                    ps.setString(3, operation.getNumber());
                    ps.execute();
                    connection.commit();
                    System.err.println("[DBOperationUpdater] - text " + operation.getTextOp() + " updated.");
                    dataWriter.updateHistory("Text updated for the operation " + operation.getId() + " and number " + operation.getNumber());
                } catch (SQLException e) {
                    System.err.println("[DBOperationUpdater] - Exception " + e + " encountered in method changeOperationText.");
                }
            } else {
                OperationNotFound operationNotFound = new OperationNotFound();
                operationNotFound.setVisible(true);
            }
        }
    }

}
