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

    public static DBOperatorDAO getInstance(){
        if(instance==null){
            instance=new DBOperatorDAO();
        }
        return instance;
    }

    /**
     * Add a new operator
     * @param number
     * @param username
     * @param password
     */
    @Override
    public void addOperatorToDatabase(String number,String username,String password) {
        connection = dBConnOp.connectToDB(connection);
        dBIns.insertOperator(connection,number,username,password);
        connection = dBConnOp.disconnectFromDB(connection);

    }

    /**
     * remove a defined operator
     * @param number
     * @param username
     */
    @Override
    public void removeOperator(String number,String username) {
        connection = dBConnOp.connectToDB(connection);
        dBDel.removeOperator(connection,number,username);
        connection = dBConnOp.disconnectFromDB(connection);
    }

    /**
     * change the password of an operator
     * @param number
     * @param username
     * @param newPassword
     */
    @Override
    public void updatePassword(String number,String username, String newPassword) {
        connection = dBConnOp.connectToDB(connection);
        dBUp.updatePassword(connection,number,username,newPassword);
        connection = dBConnOp.disconnectFromDB(connection);

    }

    /**
     * change the username of an operator
     * @param number
     * @param oldUser
     * @param newUser
     */
    @Override
    public void updateUsername(String number,String oldUser, String newUser) {
        connection = dBConnOp.connectToDB(connection);
        dBUp.updateUsername(connection,number,oldUser,newUser);
        connection = dBConnOp.disconnectFromDB(connection);
    }

    /**
     * returns a defined operator
     * @param operatorIn
     * @return
     */
    @Override
    public Operator findOperator(Operator operatorIn) {
        Operator operator=null;
        connection=dBConnOp.connectToDB(connection);
        operator=dBRet.retreiveJustTheOne(connection,operatorIn);
        connection=dBConnOp.disconnectFromDB(connection);
            return operator;
        }

    /**
     * change the status (Logged in or logged out) of an operator
      * @param number
     * @param username
     * @param status
     */
    @Override
    public void logged(String number, String username, boolean status) {
        connection=dBConnOp.connectToDB(connection);
        dBUp.logged(connection,number,username,status);
        connection=dBConnOp.disconnectFromDB(connection);
    }
}



