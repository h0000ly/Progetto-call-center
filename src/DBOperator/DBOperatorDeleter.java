package DBOperator;

import dataHistory.DataWriter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBOperatorDeleter {

    private DataWriter data;
    /**
     * This method delete an operator with a defined number and username
     * @param connection
     * @param number
     * @param username
     */
    void removeOperator(Connection connection, String numCalling,String number,String username) {
        data=new DataWriter(numCalling);
        /*boolean found=false;
        DBOperatorReader dBR=new DBOperatorReader();
        if(dBR.retrieveAllTheOperators(connection).isEmpty()){
            MasterClassExceptionGUI d=new MasterClassExceptionGUI();
            d.setVisible(true);
        }
        else {
            for (Operator a : dBR.retrieveAllTheOperators(connection)) {
                if (a.equalsIDString(number, username)) {
                    found = true;
                }
            }
            if (found) {
            */
                try {
                    System.err.println("[DBOperatorDeleter] - Removing operator in  db" + username + " number " + number + "...");
                    PreparedStatement ps = connection.prepareStatement("DELETE FROM operator WHERE username = ? AND numbe = ?;");
                    ps.setString(1, username);
                    ps.setString(2, number);
                    ps.execute();
                    connection.commit();
                    System.err.println("[DBOperatorDeleter] - Operator " + username + " number " + number + " removed.");
                    data.updateHistory("Operator " + username + " at number " + number + " removed.");
                } catch (SQLException e) {
                    System.err.println("[DBOperatorDeleter] - Exception " + e + " encountered in method removeOperator.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
    } /*else {
                OperatorNotFound d = new OperatorNotFound();
                d.setVisible(true);
            }
        }

    }*/
}
