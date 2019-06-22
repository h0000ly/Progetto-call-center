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
    private DBOperationDeleter dBDel=new DBOperationDeleter();
    private DBOperationInserter dBIns=new DBOperationInserter();
    private DBOperationUpdater dBUp=new DBOperationUpdater();
    private DBOperationReader dBRet=new DBOperationReader();
    private DBConnectionManagerOperation dBConnOp=new DBConnectionManagerOperation();

    private DBOperationDAO(){

    }

    public static DBOperationDAO getInstance(){
        if(instance==null){
            instance=new DBOperationDAO();
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
        connection = dBConnOp.connectToDB(connection);
        dBIns.addOperation(connection,numCalling,operation);
        connection = dBConnOp.disconnectFromDB(connection);

    }

    /**
     * This method is called to remove an operation in the storage unit at a certain number and id
     * @param numCalling
     * @param id
     * @param number
     */
    @Override
    public void removeOperation(String numCalling,String id,String number) {
        connection=dBConnOp.connectToDB(connection);
        dBDel.removeOperation(connection,numCalling,id,number);
        connection=dBConnOp.disconnectFromDB(connection);
    }

    /**
     * This method is called to change the id of an operation in the storage unit at a certain number and old id
     * @param numCalling
     * @param oldID
     * @param number
     * @param newID
     */
    @Override
    public void updateID(String numCalling,String oldID, String number, String newID) {
        connection =dBConnOp.connectToDB(connection);
        dBUp.changeOperationID(connection,numCalling,oldID,number,newID);
        connection=dBConnOp.disconnectFromDB(connection);
    }

    /**
     * This method is called to change the text of an operation
     * @param numCalling
     * @param operation
     */
    @Override
    public void updateText(String numCalling,Operation operation) {
        connection =dBConnOp.connectToDB(connection);
        dBUp.changeOperationText(connection,numCalling,operation);
        connection=dBConnOp.disconnectFromDB(connection);
    }

    /**
     * This method returns all the operations having a certain id and number
     * @param numCalling
     * @param numCall
     * @param numberSequence
     * @return
     */
    @Override
    public ArrayList<Operation> getAvailableOptionToShow(String numCalling,String numCall,String numberSequence) {
        ArrayList<Operation> options;
        connection=dBConnOp.connectToDB(connection);
        options=dBRet.retrieveJustTheRightOnes(connection,numCalling,numCall,numberSequence);
        connection=dBConnOp.disconnectFromDB(connection);
        return options;
    }





}
