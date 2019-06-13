package DBOperation;

import model.Operation;
import GUInterface.Exception.OperationAlreadyUsed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBOperationInserter {

    /**
     * This method add a new Operation in the database operationdb
     * @param connection
     * @param operation
     */
    public void addOperation(Connection connection,Operation operation){
        DBOperationReader dBR=new DBOperationReader();
        boolean found=false;
        for(Operation a:dBR.retrieveAllTheOperations(connection,operation.getNumber())){
            if(a.equalsID(operation)){
                found=true;
            }
        }
        if(!found) {
            try {
                System.err.println("[DBOperationInserter] - Adding operation " + operation.getId() + " to database...");
                PreparedStatement ps = connection.prepareStatement("INSERT INTO operation VALUES (?,?,?);");
                ps.setString(1, operation.getId());
                ps.setString(2, operation.getNumber());
                ps.setString(3, operation.getTextOp());
                ps.execute();
                connection.commit();
                System.err.println("[DBOperationInserter] - Operation " + operation.getId() + " added to database.");
            } catch (SQLException e) {
                System.err.println("[DBOperationInserter] - Exception " + e + " encountered in method insertOperation.");
            }
        }
        else{
            OperationAlreadyUsed b=new OperationAlreadyUsed();
            b.setVisible(true);
        }
    }


}

