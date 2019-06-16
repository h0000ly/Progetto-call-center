package DBOperator;

import model.Operator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBOperatorReader {
    /**
     * This method returns all the operators
     * @param connection
     * @return
     */
    ArrayList<Operator> retrieveAllTheOperators(Connection connection) {
        ArrayList<Operator> ops=new ArrayList<>(10);
        try {
            System.err.println("[DBOperatorReader] - Retrieving all the operators to check ...");
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM operator;");

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Operator operator=new Operator(rs.getString("numbe"),rs.getString("username"),rs.getString("passwor"));
                operator.setLoggedIn(rs.getBoolean("LoggedIn"));
                ops.add(operator);
            }
        }catch (SQLException e) {
            System.err.println("[DBOperatorReader] - Exception " + e + " encountered in method retrieveAllTheOperators.");
        }

        return ops;
    }

    /**
     * This method return only a defined operator
     * @param connection
     * @param operatorIn
     * @return
     */
    Operator retreiveJustTheOne(Connection connection,String numCalling,Operator operatorIn) {
        Operator operator = null;
        try {
            System.err.println("[DBOperatorReader] - Retrieving just a operator to check ...");
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM operator where numbe = ? and username =? and passwor =?;");
            ps.setString(1, operatorIn.getNumber());
            ps.setString(2, operatorIn.getUsername());
            ps.setString(3, operatorIn.getPassword());
            ResultSet rs = ps.executeQuery();
            rs.next();
            if(rs.getString("numbe")!=null&& rs.getString("username")!=null&& rs.getString("passwor")!=null) {
                operator = new Operator(rs.getString("numbe"), rs.getString("username"), rs.getString("passwor"));
                operator.setLoggedIn(rs.getBoolean("LoggedIn"));
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("[DBOperatorReader] - Exception " + e + " encountered in method retrieveJustTheOne.");
        }
        return operator;

    }
}
