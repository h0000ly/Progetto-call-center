package ClientServer;

import DBOperation.DBOperationDAO;
import DBOperator.DBOperatorDAO;
import dataHistory.DataWriterServer;
import model.Operation;
import model.Operator;

import java.util.ArrayList;

public class Server implements IServerProxy {
    private final  String ADDOPERATIONREQUEST="received addOperation request";
    private final  String ADDOPERATORREQUEST="received addAndRetrieveOperator request";
    private final  String RETRIEVEOPERATIONREQUEST="received retrieveTheAvailableOption request";
    private final  String CHANGEUSERNAMEREQUEST="received changeUsername request";
    private final  String CHANGEPASSWORDREQUEST="received changePassword request";
    private final  String REMOVEOPERATIONREQUEST="received removeOperation request";
    private final  String CHANGEIDREQUEST="received changeID request";
    private final  String CHANGETEXTREQUEST= "received changeText request";
    private final  String REMOVEOPERATORREQUEST= "received removeOperator request";
    private final  String FINDOPERATORREQUEST="received findOperator request ";
    private final  String LOGINREQUEST="received request to login";
    private final  String LOGOUTREQUEST="received request to logout";
    private DBOperatorDAO dBR1;
    private DBOperationDAO dBR2;
    private DataWriterServer data;
    public Server(){
        dBR1= DBOperatorDAO.getInstance();
        dBR2= DBOperationDAO.getInstance();
    }

    /**
     * This method is used to add a new operator
     * @param messageServer
     */
    public synchronized void  addOperation(MessageServer messageServer){
        data=new DataWriterServer(messageServer.getNumCalling());
        System.err.println("received addOperation request");
        data.updateHistory(ADDOPERATIONREQUEST);
        dBR2.addOperation(messageServer.getNumCalling(),messageServer.getOperation());

    }

    /**
     * This method is used to return only the available choices to show when an user is calling a number
     * @param messageServer
     * @return
     */
    public ArrayList<Operation> retrieveJustTheRight(MessageServer messageServer){
        //data=new DataWriterServer(messageServer.getNumCalling());
        ArrayList<Operation> a=dBR2.getAvailableOptionToShow(messageServer.getNumCalling(),messageServer.getNumber(),messageServer.getNumSequence());
        System.err.println("received showAvailableChoice request");
        //data.updateHistory(RETRIEVEOPERATIONREQUEST);
        return a;
    }

    /**
     * This method add a new operator and returns it
     * @param messageServer
     * @return
     */
    public synchronized Operator addAndRetrieveOperator(MessageServer messageServer){
        Operator newOperator=null;
        data=new DataWriterServer(messageServer.getNumCalling());
        data.updateHistory(ADDOPERATORREQUEST);
        newOperator=dBR1.addOperatorToDatabase(messageServer.getNumCalling(),messageServer.getOperator());
        System.err.println("received addAndRetrieveOperator request");
        return newOperator;
    }

    /**
     * This method is used to change the username af an operator
     * @param messageServer
     */
    public synchronized Operator changeUsername(MessageServer messageServer){
        Operator updatedOperator=null;
        data=new DataWriterServer(messageServer.getNumCalling());
        data.updateHistory(CHANGEUSERNAMEREQUEST);
        updatedOperator=dBR1.updateUsername(messageServer.getNumCalling(),messageServer.getId(),messageServer.getNumber(),messageServer.getText());
        System.err.println("received changeUsername request");
        return updatedOperator;
    }

    /**
     * This method is used to change the password of an operator
     * @param messageServer
     */
    public synchronized Operator changePassword(MessageServer messageServer){
        Operator updatedOperator=null;
        data=new DataWriterServer(messageServer.getNumCalling());
        data.updateHistory(CHANGEPASSWORDREQUEST);
        updatedOperator=dBR1.updatePassword(messageServer.getNumCalling(),messageServer.getOperator());
        System.err.println("received changePassword request");
        return updatedOperator;
    }

    /**
     * This method is used to delete an operation
     * @param messageServer
     */
    public synchronized void removeOperation(MessageServer messageServer){
        data=new DataWriterServer(messageServer.getNumCalling());

        data.updateHistory(REMOVEOPERATIONREQUEST);

        dBR2.removeOperation(messageServer.getNumCalling(),messageServer.getNumber(),messageServer.getNumSequence());
        System.err.println("received removeOperation request");

    }

    /**
     * This method is used to change the id of an operation
     * @param messageServer
     */
    public synchronized Operation changeID(MessageServer messageServer){
        data=new DataWriterServer(messageServer.getNumCalling());
        data.updateHistory(CHANGEIDREQUEST);
        Operation operationUpdated=dBR2.updateID(messageServer.getNumCalling(),messageServer.getId(),messageServer.getNumber(),messageServer.getText());
        System.err.println("received changeID request");
        return operationUpdated;
    }

    /**
     * This method is used to change the text of an operation
     * @param messageServer
     */
    public synchronized void changeText(MessageServer messageServer){
        data=new DataWriterServer(messageServer.getNumCalling());

        data.updateHistory(CHANGETEXTREQUEST);

        dBR2.updateText(messageServer.getNumCalling(),messageServer.getOperation());
        System.err.println("received changeText request");

    }

    /**
     * This method is used to delete the current account logged in
     * @param messageServer
     */
    public synchronized void removeOperator(MessageServer messageServer){
        data=new DataWriterServer(messageServer.getNumCalling());

        data.updateHistory(REMOVEOPERATORREQUEST);

        dBR1.removeOperator(messageServer.getNumCalling(),messageServer.getNumber(),messageServer.getNumSequence());
        System.err.println("received removeOperator request");

    }

    /**
     * This method is used to retreive a defined operator
     * @param messageServer
     * @return
     */
    public Operator findOperator(MessageServer messageServer){
        //data=new DataWriterServer(messageServer.getNumCalling());
        Operator operator=dBR1.findOperator(messageServer.getNumCalling(),messageServer.getOperator());
        System.err.println("received findOperator request");
        //data.updateHistory(FINDOPERATORREQUEST);
        return operator;
    }

    /**
     * This method is used to change the status (logged in or logged out) of an operator
     * @param messageServer
     */
    public synchronized void changeStatus(MessageServer messageServer){
        data=new DataWriterServer(messageServer.getNumCalling());
        if(messageServer.getOperator().isLoggedIn()){
            data.updateHistory(LOGINREQUEST);
        }
        else{
            data.updateHistory(LOGOUTREQUEST);
        }
        dBR1.logged(messageServer.getNumCalling(),messageServer.getOperator());
        System.err.println("received changeStatus request");

    }



}
