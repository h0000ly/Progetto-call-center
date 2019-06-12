package DBOperator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBOperatorUpdater {
    private DBOperatorReader dbR=new DBOperatorReader();

    /**
     * This method change the username of a defined operator
     * @param connection
     * @param number
     * @param oldUsername
     * @param newUsername
     */
    public void updateUsername(Connection connection, String number,String oldUsername,String newUsername) {
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
                } catch (SQLException e) {
                    System.err.println("[DBOperatorUpdater] - Exception " + e + " encounterd in method updateUsername.");
                }
            }/*
            else{
                OperatorNotFound b=new OperatorNotFound();
                b.setVisible(true);
            }
        }
    }*/

    /**
     * This method change the password of a defined operator
     * @param connection
     * @param number
     * @param username
     * @param newPassword
     */
    void updatePassword(Connection connection,String number,String username,String newPassword){
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
                    System.err.println("[DBOperatorUpdater] - Updating password " + username + "...");
                    PreparedStatement ps = connection.prepareStatement("UPDATE operator SET passwor=? WHERE username = ? AND numbe=?;");
                    ps.setString(1, newPassword);
                    ps.setString(2, username);
                    ps.setString(3, number);
                    ps.execute();
                    connection.commit();
                    System.err.println("[DBOperatorUpdater] - Password " + newPassword + " updated.");
                } catch (SQLException e) {
                    System.err.println("[DBOperatorUpdater] - Exception " + e + " encountered in method updatePassword.");
                }
            }/*
            else{
                OperatorNotFound d=new OperatorNotFound();
                d.setVisible(true);
            }
        }
    }*/
    void logged(Connection connection,String number,String username,boolean status){
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
                    System.err.println("[DBOperatorUpdater] - Updating status " + username + "...");
                    PreparedStatement ps = connection.prepareStatement("UPDATE operator SET LoggedIn =? WHERE username = ? AND numbe=?;");
                    ps.setBoolean(1, status);
                    ps.setString(2, username);
                    ps.setString(3,number);
                    ps.execute();
                    connection.commit();
                    System.err.println("[DBOperatorUpdater] - Status " + status + " updated.");
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
