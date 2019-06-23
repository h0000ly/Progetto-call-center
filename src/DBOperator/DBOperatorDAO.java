package DBOperator;

import model.Operator;

import java.sql.Connection;

public class DBOperatorDAO implements IProxyDBOperator {

    private static DBOperatorDAO instance;
    private Connection connection;
    private DBOperatorDeleter operatorDeleter = new DBOperatorDeleter();
    private DBOperatorInserter operatorInserter = new DBOperatorInserter();
    private DBOperatorUpdater operatorUpdater = new DBOperatorUpdater();
    private DBOperatorReader operatorReader = new DBOperatorReader();
    private DBConnectionManagerOperator connectionManager = new DBConnectionManagerOperator();

    private DBOperatorDAO() { }

    public static DBOperatorDAO getInstance() {
        if(instance == null){
            instance = new DBOperatorDAO();
        }
        return instance;
    }

    /**
     * This method is called to add a new operator in the database
     * @param numCalling
     * @param operator
     */

    @Override
    public Operator addOperatorToDatabase(String numCalling, Operator operator) {
        connection = connectionManager.connectToDB(connection);
        operatorInserter.insertOperator(connection, numCalling, operator);
        Operator newOperator = operatorReader.retreiveJustTheOne(connection, numCalling, operator.getNumber(), operator.getUsername());
        connection = connectionManager.disconnectFromDB(connection);
        return newOperator;
    }

    /**
     * This method is called to remove an operator from the database
     * @param numCalling
     * @param number
     * @param username
     */
    @Override
    public void removeOperator(String numCalling,String number, String username) {
        connection = connectionManager.connectToDB(connection);
        operatorDeleter.removeOperator(connection, numCalling, number, username);
        connection = connectionManager.disconnectFromDB(connection);
    }

    /**
     * This method is called to change the password of an operator
     * @param numCalling
     * @param operator
     */
    @Override
    public Operator updatePassword(String numCalling, Operator operator) {
        connection = connectionManager.connectToDB(connection);
        operatorUpdater.updatePassword(connection, numCalling, operator);
        Operator updatedOperator = operatorReader.retreiveJustTheOne(connection, numCalling, operator.getNumber(), operator.getUsername());
        connection = connectionManager.disconnectFromDB(connection);
        return updatedOperator;
    }

    /**
     * This method is called to change the username of an operator
     * @param numCalling
     * @param number
     * @param oldUser
     * @param newUser
     */
    @Override
    public Operator updateUsername(String numCalling, String number, String oldUser, String newUser) {
        connection = connectionManager.connectToDB(connection);
        operatorUpdater.updateUsername(connection, numCalling, number, oldUser, newUser);
        Operator updatedOperator = operatorReader.retreiveJustTheOne(connection, numCalling, number, newUser);
        connection = connectionManager.disconnectFromDB(connection);
        return updatedOperator;
    }

    /**
     * This method returns a defined operator
     * @param numCalling
     * @param operatorIn
     * @return
     */
    @Override
    public Operator findOperator(String numCalling,Operator operatorIn) {
        connection = connectionManager.connectToDB(connection);
        Operator operator = operatorReader.retreiveJustTheOne(connection, numCalling, operatorIn.getNumber(), operatorIn.getUsername());
        connection = connectionManager.disconnectFromDB(connection);
        return operator;
	}

    /**
     * Change the status(logged in or logged out) of an operator
     * @param numCalling
     * @param operator
     */
    @Override
    public void logged(String numCalling, Operator operator) {
        connection = connectionManager.connectToDB(connection);
        operatorUpdater.logged(connection, numCalling, operator);
        connection = connectionManager.disconnectFromDB(connection);
    }
	
}



