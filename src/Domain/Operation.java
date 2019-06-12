package Domain;

import java.io.Serializable;

public class Operation implements Serializable {
    private static final long serialVersionUID = 44154261655654454L;
    private String id;
    private String number;
    private String textOp;

    public Operation(String id,String number, String textOp) {
        this.id = id;
        this.textOp = textOp;
        this.number=number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTextOp() {
        return textOp;
    }

    public void setTextOp(String textOp) {
        this.textOp = textOp;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean equalsID(Operation op){

        if((this.getId().equals(op.getId()))&&(this.getNumber().equals(op.getNumber()))){
            return true;
        }
            return false;
    }

    public boolean equalsIDString(String id,String number){
       if((this.getId().equals(id))&&(this.getNumber().equals(number))){
           return true;
       }
       return false;
    }
}
