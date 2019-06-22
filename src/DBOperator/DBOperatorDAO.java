package DBOperator;

import model.Operator;

import java.sql.Connection;

public class DBOperatorDAO implements IProxyDBOperator {

    private static DBOperatorDAO instance;
    private Connection connection;
    private DBOperatorDeleter dBDel=new DBOperatorDeleter();
    private DBOperatorInserter dBIns=new DBOperatorInserter();
    private DBOperatorUpdater dBUp=new DBOperatorUpdater();
    private DBOperatorReader dBRet=new DBOperatorReader();
    private DBConnectionManagerOperator dBConnOp=new DBConnectionManagerOperator();

    private DBOperatorDAO(){

    }

    public static DBOperatorDAO getInstance(){
        if(instance==null){
            instance=new DBOperatorDAO();
        }
        return instance;
    }

    /**
     * This method is called to add a new operator in the database
     * @param numCalling
     * @param operator
     */

    @Override
    public void addOperatorToDatabase(String numCalling,Operator operator) {
        connection = dBConnOp.connectToDB(connection);
        dBIns.insertOperator(connection,numCalling,operator);
        connection = dBConnOp.disconnectFromDB(connection);

    }

    /**
     * This method is called to remove an operator from the database
     * @param numCalling
     * @param number
     * @param username
     */
    @Override
    public void removeOperator(String numCalling,String number,String username) {
        connection = dBConnOp.connectToDB(connection);
        dBDel.removeOperator(connection,numCalling,number,username);
        connection = dBConnOp.disconnectFromDB(connection);
    }

    /**
     * This method is called to change the password of an operator
     * @param numCalling
     * @param operator
     */
    @Override
    public void updatePassword(String numCalling,Operator operator) {
        connection = dBConnOp.connectToDB(connection);
        dBUp.updatePassword(connection,numCalling,operator);
        connection = dBConnOp.disconnectFromDB(connection);

    }

    /**
     * This method is called to change the username of an operator
     * @param numCalling
     * @param number
     * @param oldUser
     * @param newUser
     */
    @Override
    public void updateUsername(String numCalling,String number,String oldUser, String newUser) {
        connection = dBConnOp.connectToDB(connection);
        dBUp.updateUsername(connection,numCalling,number,oldUser,newUser);
        connection = dBConnOp.disconnectFromDB(connection);
    }

    /**
     * This method returns a defined operator
     * @param numCalling
     * @param operatorIn
     * @return
     */
    @Override
    public Operator findOperator(String numCalling,Operator operatorIn) {
        Operator operator=null;
        connection=dBConnOp.connectToDB(connection);
        operator=dBRet.retreiveJustTheOne(connection,numCalling,operatorIn);
        connection=dBConnOp.disconnectFromDB(connection);
        return operator;
        }

    /**
     * Change the status(logged in or logged out) of an operator
     * @param numCalling
     * @param operator
     */
    @Override
    public void logged(String numCalling, Operator operator) {

        connection=dBConnOp.connectToDB(connection);
        dBUp.logged(connection,numCalling,operator);
        connection=dBConnOp.disconnectFromDB(connection);
    }
}



