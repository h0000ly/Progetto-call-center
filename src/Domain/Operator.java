package Domain;


import java.io.Serializable;

public class Operator implements Serializable {

    private String username;
    private String password;
    private String number;
    private boolean loggedIn;
    private static final long serialVersionUID = 44154261655452454L;
    public Operator(String number,String username, String password) {
        this.username = username;
        this.password = password;
        this.number= number;
        this.loggedIn=false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean equalsOperator(Operator op){
       if((this.getUsername().equals(op.getUsername()))&&(this.getNumber().equals(op.getNumber()))){
           return true;
       }
       return false;
    }


    public boolean equalsIDString(String number,String username){
        if((this.getNumber().equals(number))&&(this.getUsername().equals(username))){
            return true;
        }
        return false;
    }
}
