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

    public static DBOperationDAO getInstance(){
        if(instance==null){
            instance=new DBOperationDAO();
        }
        return instance;
    }

    /**
     * add a new operation
     * @param operation
     */
    @Override
    public void addOperationToDatabase(String numCalling,Operation operation) {
        connection = dBConnOp.connectToDB(connection);
        dBIns.addOperation(connection,numCalling,operation);
        connection = dBConnOp.disconnectFromDB(connection);

    }

    /**
     * remove an operation
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
     * change the id of an operation
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
     * Change the textof an operation
     * @param operation
     */
    @Override
    public void updateText(String numCalling,Operation operation) {
        connection =dBConnOp.connectToDB(connection);
        dBUp.changeOperationText(connection,numCalling,operation);
        connection=dBConnOp.disconnectFromDB(connection);
    }

    /**
     * returns all the available choices for the number called
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
