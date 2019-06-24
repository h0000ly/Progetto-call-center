package ClientServer;

import DBOperation.DBOperationDAO;
import DBOperator.DBOperatorDAO;
import dataHistory.DataWriterServer;
import model.Operation;
import model.Operator;

import java.util.ArrayList;

public class Server implements IServerProxy {
	
	// variables
	private DBOperatorDAO OperatorDAO;
    private DBOperationDAO OperationDAO;
    private DataWriterServer dataWriter;
	// messages
    private final String ADDOPERATIONREQUEST = "received addOperation request";
    private final String ADDOPERATORREQUEST = "received addAndRetrieveOperator request";
    private final String RETRIEVEOPERATIONREQUEST = "received retrieveJustTheRight request";
    private final String CHANGEUSERNAMEREQUEST = "received changeUsername request";
    private final String CHANGEPASSWORDREQUEST = "received changePassword request";
    private final String REMOVEOPERATIONREQUEST = "received removeOperation request";
    private final String CHANGEIDREQUEST = "received changeID request";
    private final String CHANGETEXTREQUEST= "received changeText request";
    private final String REMOVEOPERATORREQUEST= "received removeOperator request";
    private final String FINDOPERATORREQUEST = "received findOperator request ";
    private final String LOGINREQUEST = "received request to login";
    private final String LOGOUTREQUEST = "received request to logout";
    
    public Server() {
        OperatorDAO = DBOperatorDAO.getInstance();
        OperationDAO = DBOperationDAO.getInstance();
    }

    /**
     * This method is used to add a new operator
     * @param messageServer
     */
    public synchronized void addOperation(MessageServer messageServer) {
		logOperation(new DataWriterServer(messageServer.getNumCalling()), ADDOPERATIONREQUEST);
        OperationDAO.addOperation(messageServer.getNumCalling(), messageServer.getOperation());
    }

    /**
     * This method is used to return only the available choices to show when an user is calling a number
     * @param messageServer
     * @return the available operations
     */
    public ArrayList<Operation> retrieveJustTheRight(MessageServer messageServer) {
        ArrayList<Operation> operations = OperationDAO.getAvailableOptionToShow(messageServer.getNumCalling(), messageServer.getNumber(), messageServer.getNumSequence());
		logOperation(new DataWriterServer(messageServer.getNumCalling()), RETRIEVEOPERATIONREQUEST);
        return operations;
    }

    /**
     * This method add a new operator and returns it
     * @param messageServer
     * @return
     */
    public synchronized Operator addAndRetrieveOperator(MessageServer messageServer) {
        Operator newOperator = OperatorDAO.addOperatorToDatabase(messageServer.getNumCalling(), messageServer.getOperator());
		logOperation(new DataWriterServer(messageServer.getNumCalling()), ADDOPERATORREQUEST);
        return newOperator;
    }

    /**
     * This method is used to change the username af an operator
     * @param messageServer
     */
    public synchronized Operator changeUsername(MessageServer messageServer) {
        Operator updatedOperator = OperatorDAO.updateUsername(messageServer.getNumCalling(), messageServer.getId(), messageServer.getNumber(), messageServer.getText());
		logOperation(new DataWriterServer(messageServer.getNumCalling()), CHANGEUSERNAMEREQUEST);
        return updatedOperator;
    }

    /**
     * This method is used to change the password of an operator
     * @param messageServer
     */
    public synchronized Operator changePassword(MessageServer messageServer) {
        Operator updatedOperator = OperatorDAO.updatePassword(messageServer.getNumCalling(), messageServer.getOperator());
		logOperation(new DataWriterServer(messageServer.getNumCalling()), CHANGEPASSWORDREQUEST);
        return updatedOperator;
    }

    /**
     * This method is used to delete an operation
     * @param messageServer
     */
    public synchronized void removeOperation(MessageServer messageServer) {
        OperationDAO.removeOperation(messageServer.getNumCalling(), messageServer.getNumber(), messageServer.getNumSequence());
		logOperation(new DataWriterServer(messageServer.getNumCalling()), REMOVEOPERATIONREQUEST);
    }

    /**
     * This method is used to change the id of an operation
     * @param messageServer
     */
    public synchronized Operation changeID(MessageServer messageServer) {
        Operation operationUpdated = OperationDAO.updateID(messageServer.getNumCalling(), messageServer.getId(), messageServer.getNumber(), messageServer.getText());
		logOperation(new DataWriterServer(messageServer.getNumCalling()), CHANGEIDREQUEST);
        return operationUpdated;
    }

    /**
     * This method is used to change the text of an operation
     * @param messageServer
     */
    public synchronized void changeText(MessageServer messageServer) {
        OperationDAO.updateText(messageServer.getNumCalling(), messageServer.getOperation());
		logOperation(new DataWriterServer(messageServer.getNumCalling()), CHANGETEXTREQUEST);
    }

    /**
     * This method is used to delete the current account logged in
     * @param messageServer
     */
    public synchronized void removeOperator(MessageServer messageServer) {
        OperatorDAO.removeOperator(messageServer.getNumCalling(), messageServer.getNumber(), messageServer.getNumSequence());
		logOperation(new DataWriterServer(messageServer.getNumCalling()), REMOVEOPERATORREQUEST);
    }

    /**
     * This method is used to retreive a defined operator
     * @param messageServer
     * @return
     */
    public Operator findOperator(MessageServer messageServer) {
        Operator operator = OperatorDAO.findOperator(messageServer.getNumCalling(), messageServer.getOperator());
		logOperation(new DataWriterServer(messageServer.getNumCalling()), FINDOPERATORREQUEST);
        return operator;
    }

    /**
     * This method is used to change the status (logged in or logged out) of an operator
     * @param messageServer
     */
    public synchronized void changeStatus(MessageServer messageServer) {
        OperatorDAO.logged(messageServer.getNumCalling(), messageServer.getOperator());
        if (messageServer.getOperator().isLoggedIn()) {
			logOperation(new DataWriterServer(messageServer.getNumCalling()), LOGINREQUEST);
        } else {
			logOperation(new DataWriterServer(messageServer.getNumCalling()), LOGOUTREQUEST);
        }
    }
	
	private void logOperation(DataWriterServer dataWriter, String operation) {
		System.err.println(operation);
		dataWriter.updateHistory(operation);
	}

}
