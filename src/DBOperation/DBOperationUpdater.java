package DBOperation;

import Domain.Operation;
import GUInterface.Exception.DBOperationEmpty;
import GUInterface.Exception.OperationNotFound;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBOperationUpdater {
    DBOperationReader dBR=new DBOperationReader();
    /**
     * This method change the id of an operation
     * @param connection
     * @param oldId
     * @param number
     * @param newId
     */

    void changeOperationID(Connection connection,String oldId,String number,String newId){
        boolean found =false;
        if(dBR.retrieveAllTheOperations(connection,number).isEmpty()){
            DBOperationEmpty d=new DBOperationEmpty();
            d.setVisible(true);
        }
        else {
            for (Operation a : dBR.retrieveAllTheOperations(connection,number)) {
                if (a.equalsIDString(oldId, number)) {
                    found = true;
                }
            }
            if (found) {
                try {
                    System.err.println("[DBOperationUpdater] - Updating id " + oldId);
                    PreparedStatement ps = connection.prepareStatement("UPDATE operation SET id = ? WHERE id = ? AND numbe = ?;");
                    ps.setString(1, newId);
                    ps.setString(2, oldId);
                    ps.setString(3, number);
                    ps.execute();
                    connection.commit();
                    System.err.println("[DBOperationUpdater] - id "+oldId+" changed in " + newId );
                } catch (SQLException e) {
                    System.err.println("[DBOperationUpdater] - Exception " + e + " encountered in method changeOperationID.");
                }
            } else {
                OperationNotFound b = new OperationNotFound();
                b.setVisible(true);
            }
        }
    }

    /**
     * This method change the text of an operation
     * @param connection
     * @param id
     * @param number
     * @param text
     */
    void changeOperationText(Connection connection,String id,String number,String text) {
        boolean found=false;
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
                    System.err.println("[DBOperationUpdater] - Updating text at " + id );
                    PreparedStatement ps = connection.prepareStatement("UPDATE operation SET tex= ? WHERE id = ? AND numbe= ?;");
                    ps.setString(1, text);
                    ps.setString(2, id);
                    ps.setString(3, number);
                    ps.execute();
                    connection.commit();
                    System.err.println("[DBOperationUpdater] - text " + text + " updated.");
                } catch (SQLException e) {
                    System.err.println("[DBOperationUpdater] - Exception " + e + " encountered in method changeOperationText.");
                }
            } else {
                OperationNotFound b = new OperationNotFound();
                b.setVisible(true);
            }
        }
    }

}
