package ClientServer;

import Domain.Operator;

import java.io.Serializable;

public class MessageServer implements Serializable {

    private MessageType messageType;
    private String id;
    private String number;
    private String text;
    private String numSequence;
    private Operator operator;
    private String username;
    private boolean status;
    private static final long serialVersionUID = 44154261654512454L;


    public MessageServer(MessageType messageType, String id, String number, String text) {
        this.messageType = messageType;
        this.id = id;
        this.number = number;
        this.text = text;
    }

    public MessageServer(MessageType messageType,String number,String numSequence){
        this.messageType=messageType;
        this.numSequence=numSequence;
        this.number=number;
    }

    public MessageServer(MessageType messageType, Operator operator){
        this.messageType =messageType;
        this.operator=operator;
    }

    public MessageServer(MessageType messageType,String number, String username,boolean status){
        this.messageType=messageType;
        this.number=number;
        this.username=username;
        this.status=status;
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

    public String getUsername() {
        return username;
    }

    public boolean isStatus() {
        return status;
    }
}
