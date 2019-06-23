package DBOperation;

import model.Operation;

import java.sql.Connection;
import java.util.ArrayList;


/**
 * This class contains all the available methods for the database of the operations
 */
public class DBOperationDAO implements IDBOperationProxy {

    private static DBOperationDAO instance;
    private Connection connection;
    private DBOperationDeleter operationDeleter = new DBOperationDeleter();
    private DBOperationInserter operationInserter = new DBOperationInserter();
    private DBOperationUpdater operationUpdater = new DBOperationUpdater();
    private DBOperationReader operationReader = new DBOperationReader();
    private DBConnectionManagerOperation connectionManager = new DBConnectionManagerOperation();

    private DBOperationDAO() { }

    public static DBOperationDAO getInstance() {
        if (instance == null) {
            instance = new DBOperationDAO();
        }
        return instance;
    }

    /**
     * This method is called to add a new operation in the storage unit
     * @param numCalling
     * @param operation
     */
    @Override
    public void addOperation(String numCalling, Operation operation) {
        connection = connectionManager.connectToDB(connection);
        operationInserter.addOperation(connection, numCalling, operation);
        connection = connectionManager.disconnectFromDB(connection);
    }

    /**
     * This method is called to remove an operation in the storage unit at a certain number and id
     * @param numCalling
     * @param id
     * @param number
     */
    @Override
    public void removeOperation(String numCalling,String id,String number) {
        connection = connectionManager.connectToDB(connection);
        operationDeleter.removeOperation(connection, numCalling, id, number);
        connection = connectionManager.disconnectFromDB(connection);
    }

    /**
     * This method is called to change the id of an operation in the storage unit at a certain number and old id
     * @param numCalling
     * @param oldID
     * @param number
     * @param newID
     */
    @Override
    public Operation updateID(String numCalling, String oldID, String number, String newID) {
        connection = connectionManager.connectToDB(connection);
        operationUpdater.changeOperationID(connection, numCalling, oldID, number, newID);
        ArrayList<Operation> operations = operationReader.retrieveAllTheOperations(connection,number);
        connection=connectionManager.disconnectFromDB(connection);
        for (Operation operation : operations) {
            if (operation.equalsIDString(newID, number)) {
                return operation;
            }
        }
        return null;
    }

    /**
     * This method is called to change the text of an operation
     * @param numCalling
     * @param operation
     */
    @Override
    public void updateText(String numCalling, Operation operation) {
        connection = connectionManager.connectToDB(connection);
        operationUpdater.changeOperationText(connection, numCalling, operation);
        connection = connectionManager.disconnectFromDB(connection);
    }

    /**
     * This method returns all the operations having a certain id and number
     * @param numCalling
     * @param numCall
     * @param numberSequence
     * @return
     */
    @Override
    public ArrayList<Operation> getAvailableOptionToShow(String numCalling, String numCall, String numberSequence) {
        connection = connectionManager.connectToDB(connection);
        ArrayList<Operation> options = operationReader.retrieveJustTheRightOnes(connection, numCalling, numCall, numberSequence);
        connection = connectionManager.disconnectFromDB(connection);
        return options;
    }

}
