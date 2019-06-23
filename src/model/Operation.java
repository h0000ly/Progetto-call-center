package model;

import java.io.Serializable;

public class Operation implements Serializable {
	
    private static final long serialVersionUID = 44154261655654454L;
    private String id;
    private String number;
    private String textOp;

    public Operation(String id, String number, String textOp) {
        this.id = id;
        this.textOp = textOp;
        this.number=number;
    }

    /**
     * This method returns the id of an operation
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * This method set the id of an operation
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method return the text of an operation
     * @return textOp
     */
    public String getTextOp() {
        return textOp;
    }

    /**
     * This method set the text of an operation
     * @param textOp
     */
    public void setTextOp(String textOp) {
        this.textOp = textOp;
    }

    /**
     * This method returns the number of an operation
     * @return
     */
    public String getNumber() {
        return number;
    }

    /**
     * This method set the number of an operation
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * This method checks if two operation are equal
     * @param op
     * @return
     */
    public boolean equals(Operation op){
        return (this.getId().equals(op.getId())) && (this.getNumber().equals(op.getNumber()));
    }

    /**
     * This method check if two operation are equals
     * @param id
     * @param number
     * @return
     */
    public boolean equalsIDString(String id,String number){
       return (this.getId().equals(id)) && (this.getNumber().equals(number));
    }
}
