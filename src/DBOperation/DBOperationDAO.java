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
    public void addOperationToDatabase(Operation operation) {
        connection = dBConnOp.connectToDB(connection);
        dBIns.addOperation(connection,operation);
        connection = dBConnOp.disconnectFromDB(connection);

    }

    /**
     * remove an operation
     * @param id
     * @param number
     */
    @Override
    public void removeOperation(String id,String number) {
        connection=dBConnOp.connectToDB(connection);
        dBDel.removeOperation(connection,id,number);
        connection=dBConnOp.disconnectFromDB(connection);
    }

    /**
     * change the id of an operation
     * @param oldID
     * @param number
     * @param newID
     */
    @Override
    public void updateID(String oldID, String number, String newID) {
        connection =dBConnOp.connectToDB(connection);
        dBUp.changeOperationID(connection,oldID,number,newID);
        connection=dBConnOp.disconnectFromDB(connection);
    }

    /**
     * change the text of an operation
     * @param id
     * @param number
     * @param newText
     */
    @Override
    public void updateText(String id, String number, String newText) {
        connection =dBConnOp.connectToDB(connection);
        dBUp.changeOperationText(connection,id,number,newText);
        connection=dBConnOp.disconnectFromDB(connection);
    }

    /**
     * returns all the available choices for the number called
     * @param numCall
     * @param numberSequence
     * @return
     */
    @Override
    public ArrayList<String> getAvailableOptionToShow(String numCall,String numberSequence) {
        ArrayList<String> options=new ArrayList<>();
        connection=dBConnOp.connectToDB(connection);
        for(Operation b:dBRet.retrieveJustTheRightOnes(connection,numCall,numberSequence)){
            options.add(b.getId().charAt(b.getId().length()-1)+" - "+b.getTextOp());
        }
        connection=dBConnOp.disconnectFromDB(connection);
        return options;
    }




}
