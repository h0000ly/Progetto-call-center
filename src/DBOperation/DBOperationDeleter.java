package DBOperation;

import dataHistory.DataWriter;
import model.Operation;
import GUInterface.Exception.DBOperationEmpty;
import GUInterface.Exception.OperationNotFound;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DBOperationDeleter {
    private DataWriter data;
    /**
     * Remove the Operation identified by id and number in the database of the operations
     *
     * @param connection
     * @param id
     * @param number
     */
    void removeOperation(Connection connection, String numCalling,String id, String number) {
        boolean found=false;
        data=new DataWriter(numCalling);
        DBOperationReader dBR=new DBOperationReader();
        if(dBR.retrieveAllTheOperations(connection,number).isEmpty()){
            DBOperationEmpty d=new DBOperationEmpty();
            d.setVisible(true);
        }
        else {

            for (Operation a : dBR.retrieveAllTheOperations(connection,number)) {
                if (a.equalsIDString(id, number)) {
                    found = true;
                }
            }
            if (found) {
                try {
                    System.err.println("[DBOperationDeleter] - Removing operation in  db " + id + " number " + number + "...");
                    PreparedStatement ps = connection.prepareStatement("DELETE FROM operation WHERE id = ? AND numbe = ?;");
                    ps.setString(1, id);
                    ps.setString(2, number);
                    ps.execute();
                    connection.commit();
                    System.err.println("[DBOperationDeleter] - Operation " + id + " number " + number + " removed.");
                    data.updateHistory("Operation " + id + " at number " + number + " removed.");
                } catch (SQLException e) {
                    System.err.println("[DBOperationDeleter] - Exception " + e + " encountered in method removeOperation.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                OperationNotFound b = new OperationNotFound();
                b.setVisible(true);
            }
        }
    }
}


