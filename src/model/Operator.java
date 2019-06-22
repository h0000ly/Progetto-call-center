package model;


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

    /**
     * This method returns the username of an operator
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method set the username of an operator
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method returns the password of an operator
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method set the password of an operator
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method returns the status(logged in or logged out) of an operator
     * @return
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * This method set the status(logged in or logged out) of an operator
     * @param loggedIn
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     * This method returns the number of an operator
     * @return
     */
    public String getNumber() {
        return number;
    }

    /**
     * This method set the number of an operator
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * This method checks if two operators are equal
     * @param op
     * @return
     */
    public boolean equals(Operator op){
       if((this.getUsername().equals(op.getUsername()))&&(this.getNumber().equals(op.getNumber()))){
           return true;
       }
       return false;
    }

}
