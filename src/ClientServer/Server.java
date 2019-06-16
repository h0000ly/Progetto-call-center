package ClientServer;

import DBOperation.DBOperationDAO;
import DBOperator.DBOperatorDAO;
import dataHistory.DataWriter;
import model.Operation;
import model.Operator;

import java.io.IOException;
import java.util.ArrayList;

public class Server implements IServerProxy {
    private DBOperatorDAO dBR1;
    private DBOperationDAO dBR2;
    private DataWriter data;
    public Server(){
        dBR1= DBOperatorDAO.getInstance();
        dBR2= DBOperationDAO.getInstance();

    }

    /**
     * This method is able to add a new operator
     * @param messageServer
     */
    public synchronized void  addOperation(MessageServer messageServer){
        data=new DataWriter(messageServer.getNumCalling());
        System.err.println("received addOperation request");
         try {
            data.updateHistory("received addOperation request");
        } catch (IOException e) {
            e.printStackTrace();
        }
        dBR2.addOperationToDatabase(messageServer.getNumCalling(),messageServer.getOperation());

    }

    /**
     * This method is able to return only the available choices to show when an user is calling a number
     * @param messageServer
     * @return
     */
    public ArrayList<Operation> retrieveJustTheRight(MessageServer messageServer){
        ArrayList<Operation> a=dBR2.getAvailableOptionToShow(messageServer.getNumCalling(),messageServer.getNumber(),messageServer.getNumSequence());
        System.err.println("received showAvailableChoice request");
        return a;
    }

    /**
     * This method add a new operator and returns it
     * @param messageServer
     * @return
     */
    public synchronized Operator addAndRetrieveOperator(MessageServer messageServer){
        data=new DataWriter(messageServer.getNumCalling());
        try {
            data.updateHistory("received addOperator request");
        } catch (IOException e) {
            e.printStackTrace();
        }
        dBR1.addOperatorToDatabase(messageServer.getNumCalling(),messageServer.getOperator());
        System.err.println("received addAndRetrieveOperator request");

        return messageServer.getOperator();
    }

    /**
     * This method is used to change the username af an operator
     * @param messageServer
     */
    public synchronized void changeUsername(MessageServer messageServer){
        data=new DataWriter(messageServer.getNumCalling());
        try {
            data.updateHistory("received changeUsername request");
        } catch (IOException e) {
            e.printStackTrace();
        }
        dBR1.updateUsername(messageServer.getNumCalling(),messageServer.getId(),messageServer.getNumber(),messageServer.getText());

        System.err.println("received changeUsername request");
    }

    /**
     * This method is used to change the password of an operator
     * @param messageServer
     */
    public synchronized void changePassword(MessageServer messageServer){
        data=new DataWriter(messageServer.getNumCalling());
        try {
            data.updateHistory("received changePassword request");
        } catch (IOException e) {
            e.printStackTrace();
        }
        dBR1.updatePassword(messageServer.getNumCalling(),messageServer.getOperator());
        System.err.println("received changePassword request");

    }

    /**
     * This method is used to delete an operation
     * @param messageServer
     */
    public synchronized void removeOperation(MessageServer messageServer){
        data=new DataWriter(messageServer.getNumCalling());
        try {
            data.updateHistory("received removeOperation request");
        } catch (IOException e) {
            e.printStackTrace();
        }
        dBR2.removeOperation(messageServer.getNumCalling(),messageServer.getNumber(),messageServer.getNumSequence());
        System.err.println("received removeOperation request");

    }

    /**
     * This method is used to change the id of an operation
     * @param messageServer
     */
    public synchronized void changeID(MessageServer messageServer){
        data=new DataWriter(messageServer.getNumCalling());
        try {
            data.updateHistory("received changeID request");
        } catch (IOException e) {
            e.printStackTrace();
        }
        dBR2.updateID(messageServer.getNumCalling(),messageServer.getId(),messageServer.getNumber(),messageServer.getText());
        System.err.println("received changeID request");

    }

    /**
     * This method is used to change the text of an operation
     * @param messageServer
     */
    public synchronized void changeText(MessageServer messageServer){
        data=new DataWriter(messageServer.getNumCalling());
        try {
            data.updateHistory("received changeText request");
        } catch (IOException e) {
            e.printStackTrace();
        }
        dBR2.updateText(messageServer.getNumCalling(),messageServer.getOperation());
        System.err.println("received changeText request");

    }

    /**
     * This method is used to delete the current account logged in
     * @param messageServer
     */
    public synchronized void removeOperator(MessageServer messageServer){
        data=new DataWriter(messageServer.getNumCalling());
        try {
            data.updateHistory("received removeOperator request");
        } catch (IOException e) {
            e.printStackTrace();
        }
        dBR1.removeOperator(messageServer.getNumCalling(),messageServer.getNumber(),messageServer.getNumSequence());
        System.err.println("received removeOperator request");

    }

    /**
     * This method is used to retreive a defined operator
     * @param messageServer
     * @return
     */
    public Operator findOperator(MessageServer messageServer){
        Operator operator=dBR1.findOperator(messageServer.getNumCalling(),messageServer.getOperator());
        System.err.println("received findOperator request");
        return operator;
    }

    /**
     * This method is used to change the status (logged in or logged out) of an operator
     * @param messageServer
     */
    public synchronized void changeStatus(MessageServer messageServer){
        data=new DataWriter(messageServer.getNumCalling());
        if(messageServer.getOperator().isLoggedIn()){
            try {
                data.updateHistory("received request to login");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else{
            try {
                data.updateHistory("received request to logout");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dBR1.logged(messageServer.getNumCalling(),messageServer.getOperator());
        System.err.println("received changeStatus request");

    }



}
