package ClientServer;

import DBOperation.DBOperationDAO;
import DBOperator.DBOperatorDAO;
import model.Operation;
import model.Operator;

import java.util.ArrayList;

public class Server implements IServerProxy {
    private DBOperatorDAO dBR1;
    private DBOperationDAO dBR2;
    public Server(){
        dBR1= DBOperatorDAO.getInstance();
        dBR2= DBOperationDAO.getInstance();
    }

    /**
     * This method is able to add a new operator
     * @param messageServer
     */
    public synchronized void  addOperation(MessageServer messageServer){
        Operation operation= new Operation(messageServer.getId(),messageServer.getNumber(),messageServer.getText());
        System.err.println("received addOperation request");
        dBR2.addOperationToDatabase(operation);
    }

    /**
     * This method is able to return only the available choices to show when an user is calling a number
     * @param messageServer
     * @return
     */
    public ArrayList<String> retrieveJustTheRight(MessageServer messageServer){
        ArrayList<String> a=dBR2.getAvailableOptionToShow(messageServer.getNumber(),messageServer.getNumSequence());
        System.err.println("received showAvailableChoice request");
        return a;
    }

    /**
     * This method add a new operator and returns it
     * @param messageServer
     * @return
     */
    public synchronized Operator addAndRetrieveOperator(MessageServer messageServer){
        dBR1.addOperatorToDatabase(messageServer.getId(),messageServer.getNumber(),messageServer.getText());
        System.err.println("received addAndRetreiveOperator request");
        Operator operator=new Operator(messageServer.getId(),messageServer.getNumber(),messageServer.getText());
        return operator;
    }

    /**
     * This method is used to change the username af an operator
     * @param messageServer
     */
    public synchronized void changeUsername(MessageServer messageServer){
        dBR1.updateUsername(messageServer.getId(),messageServer.getNumber(),messageServer.getText());
        System.err.println("received changeUsername request");
    }

    /**
     * This method is used to change the password of an operator
     * @param messageServer
     */
    public synchronized void changePassword(MessageServer messageServer){
        dBR1.updatePassword(messageServer.getId(),messageServer.getNumber(),messageServer.getText());
        System.err.println("received changePassword request");
    }

    /**
     * This method is used to delete an operation
     * @param messageServer
     */
    public synchronized void removeOperation(MessageServer messageServer){
        dBR2.removeOperation(messageServer.getNumber(),messageServer.getNumSequence());
        System.err.println("received removeOperation request");
    }

    /**
     * This method is used to change the id of an operation
     * @param messageServer
     */
    public synchronized void changeID(MessageServer messageServer){
        dBR2.updateID(messageServer.getId(),messageServer.getNumber(),messageServer.getText());
        System.err.println("received changeID request");
    }

    /**
     * This method is used to change the text of an operation
     * @param messageServer
     */
    public synchronized void changeText(MessageServer messageServer){
        dBR2.updateText(messageServer.getId(),messageServer.getNumber(),messageServer.getText());
        System.err.println("received changeText request");
    }

    /**
     * This method is used to delete the current account logged in
     * @param messageServer
     */
    public synchronized void removeOperator(MessageServer messageServer){
        dBR1.removeOperator(messageServer.getNumber(),messageServer.getNumSequence());
        System.err.println("received removeOperator request");
    }

    /**
     * This method is used to retreive a defined operator
     * @param messageServer
     * @return
     */
    public Operator findOperator(MessageServer messageServer){
        Operator operator=dBR1.findOperator(messageServer.getOperator());
        System.err.println("received findOperator request");
        return operator;
    }

    /**
     * This method is used to change the status (logged in or logged out) of an operator
     * @param messageServer
     */
    public synchronized void changeStatus(MessageServer messageServer){
        dBR1.logged(messageServer.getNumber(),messageServer.getUsername(),messageServer.isStatus());
        System.err.println("received changeStatus request");

    }



}
