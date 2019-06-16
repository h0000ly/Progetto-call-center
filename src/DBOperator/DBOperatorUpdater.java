package DBOperator;

import dataHistory.DataWriter;
import model.Operator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBOperatorUpdater {
    private DataWriter data;

    /**
     * This method change the username of a defined operator
     * @param connection
     * @param number
     * @param oldUsername
     * @param newUsername
     */
    public void updateUsername(Connection connection,String numCalling, String number,String oldUsername,String newUsername) {
        data=new DataWriter(numCalling);
        /*boolean found=false;
        if(dbR.retrieveAllTheOperators(connection).isEmpty()){
            MasterClassExceptionGUI d=new MasterClassExceptionGUI();
            d.setVisible(true);
        }
        else{
            for(Operator a:dbR.retrieveAllTheOperators(connection)){
                if(a.equalsIDString(number,oldUsername)){
                    found=true;
                }
            }

            if(found){*/
                try {
                    System.err.println("[DBOperatorUpdater] - Updating username " + oldUsername + "...");
                    PreparedStatement ps = connection.prepareStatement("UPDATE operator SET username= ? WHERE username = ? AND numbe= ?;");
                    ps.setString(1, newUsername);
                    ps.setString(2, oldUsername);
                    ps.setString(3, number);
                    ps.execute();
                    connection.commit();
                    System.err.println("[DBOperatorUpdater] - Username " + newUsername + " updated.");
                    data.updateHistory("Username updated, new username: "+newUsername);
                } catch (SQLException e) {
                    System.err.println("[DBOperatorUpdater] - Exception " + e + " encounterd in method updateUsername.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }/*
            else{
                OperatorNotFound b=new OperatorNotFound();
                b.setVisible(true);
            }
        }
    }*/


    void updatePassword(Connection connection,String numCalling,Operator operator){
        data=new DataWriter(numCalling);
        /*boolean found=false;

        if(dbR.retrieveAllTheOperators(connection).isEmpty()){
            MasterClassExceptionGUI d=new MasterClassExceptionGUI();
            d.setVisible(true);
        }
        else{
            for(Operator a:dbR.retrieveAllTheOperators(connection)){
                if(a.equalsIDString(number,username));
                found=true;
            }
            if(found){*/
                try {
                    System.err.println("[DBOperatorUpdater] - Updating password for " + operator.getUsername() + "...");
                    PreparedStatement ps = connection.prepareStatement("UPDATE operator SET passwor=? WHERE username = ? AND numbe=?;");
                    ps.setString(1, operator.getPassword());
                    ps.setString(2, operator.getUsername());
                    ps.setString(3, operator.getNumber());
                    ps.execute();
                    connection.commit();
                    System.err.println("[DBOperatorUpdater] - Password " + operator.getPassword() + " updated.");
                    data.updateHistory("Password uptated for the operator "+operator.getUsername()+" and number "+operator.getNumber());
                } catch (SQLException e) {
                    System.err.println("[DBOperatorUpdater] - Exception " + e + " encountered in method updatePassword.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }/*
            else{
                OperatorNotFound d=new OperatorNotFound();
                d.setVisible(true);
            }
        }
    }*/
    void logged(Connection connection, String numCalling,Operator operator){
        /*boolean found=false;

        if(dbR.retrieveAllTheOperators(connection).isEmpty()){
            MasterClassExceptionGUI d=new MasterClassExceptionGUI();
            d.setVisible(true);
        }
        else{
            for(Operator a:dbR.retrieveAllTheOperators(connection)){
                if(a.equalsIDString(number,username));
                found=true;
            }
            if(found){*/
                try {
                    System.err.println("[DBOperatorUpdater] - Updating status of the operator" + operator.getUsername() + "...");
                    PreparedStatement ps = connection.prepareStatement("UPDATE operator SET LoggedIn =? WHERE username = ? AND numbe=?;");
                    ps.setBoolean(1, operator.isLoggedIn());
                    ps.setString(2, operator.getUsername());
                    ps.setString(3,operator.getNumber());
                    ps.execute();
                    connection.commit();
                    System.err.println("[DBOperatorUpdater] - Status " + operator.isLoggedIn() + " updated.");
                } catch (SQLException e) {
                    System.err.println("[DBOperatorUpdater] - Exception " + e + " encountered in method logged.");
                }
            }/*
            else{
                OperatorNotFound d=new OperatorNotFound();
                d.setVisible(true);
            }
        }


    }*/


}
