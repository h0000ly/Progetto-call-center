package DBOperator;


import model.Operator;

public interface IProxyDBOperator {

    void addOperatorToDatabase(String numCalling,Operator operator);
    void removeOperator(String numCalling,String number,String username);
    void updatePassword(String numCalling,Operator operator);
    void updateUsername(String numCalling,String number,String oldUsername, String newUsername);
    Operator findOperator(String numCalling,Operator operator);
    void logged(String numCalling,Operator operator);


}
