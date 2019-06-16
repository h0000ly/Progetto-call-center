package ClientServer;

import model.Operation;
import model.Operator;

import java.io.Serializable;

public class MessageServer implements Serializable {
    private String numCalling;
    private MessageType messageType;
    private String id;
    private String number;
    private String text;
    private String numSequence;
    private Operator operator;
    private Operation operation;
    private static final long serialVersionUID = 44154261654512454L;


    public MessageServer(MessageType messageType,String numCalling, String id, String number, String text) {
        this.messageType = messageType;
        this.id = id;
        this.number = number;
        this.text = text;
        this.numCalling=numCalling;
    }

    public MessageServer(MessageType messageType,String numCalling,String number,String numSequence){
        this.messageType=messageType;
        this.numSequence=numSequence;
        this.number=number;
        this.numCalling=numCalling;
    }

    public MessageServer(MessageType messageType,String numCalling, Operator operator){
        this.messageType =messageType;
        this.operator=operator;
        this.numCalling=numCalling;
    }

    public MessageServer(MessageType messageType, String numCalling,Operation operation){
        this.messageType=messageType;
        this.operation=operation;
        this.numCalling=numCalling;
    }



    public MessageType getMessageType() {
        return messageType;
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public String getNumSequence() {
        return numSequence;
    }

    public Operator getOperator() {
        return operator;
    }

    public Operation getOperation() {
        return operation;
    }

    public String getNumCalling() {
        return numCalling;
    }
}
