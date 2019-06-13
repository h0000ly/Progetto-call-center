package DBOperation;

import model.Operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBOperationReader {
    /**
     * This method returns all the operation contained in the database for the number called
     * @param connection
     * @param number
     * @return
     */

    ArrayList<Operation> retrieveAllTheOperations(Connection connection,String number) {
        ArrayList<Operation> ops=new ArrayList<>(10);
        try {
            System.err.println("[DBOperationReader] - Retrieving all the operators to check ...");
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM operation where numbe=?;");
            ps.setString(1,number);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                ops.add(new Operation(rs.getString("id"),rs.getString("numbe"),rs.getString("tex")));
            }
        }catch (SQLException e) {
            System.err.println("[DBOperationReader] - Exception " + e + " encountered in method retrieveAllTheOperations.");
        }
        return ops;
    }

    /**
     * This method return all the next choices for the caller
     * @param connection
     * @param numCall
     * @param numSequence
     * @return ArrayList<Operation>
     */
    ArrayList<Operation> retrieveJustTheRightOnes(Connection connection,String numCall,String numSequence){
        ArrayList<Operation> ops=new ArrayList<>();
        try{
            System.err.println("[DBOperationReader] - Retrieving just the right ones ... ");
            PreparedStatement ps=connection.prepareStatement("SELECT * From operation where numbe= ? and id like ? order by id;");
            ps.setString(1,numCall);
            ps.setString(2,numSequence+"_");
            ResultSet rs=ps.executeQuery();
                while(rs.next()){

                    ops.add(new Operation(rs.getString("id"),rs.getString("numbe"),rs.getString("tex")));
            }
        }
         catch (SQLException e) {
             System.err.println("[DBOperationReader] - Exception " + e + " encountered in method retrieveJustTheRightOnes.");
        }
        return ops;
    }


}
