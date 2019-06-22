package DBOperator;


import model.Operator;

public interface IProxyDBOperator {

    Operator addOperatorToDatabase(String numCalling,Operator operator);
    void removeOperator(String numCalling,String number,String username);
    Operator updatePassword(String numCalling,Operator operator);
    Operator updateUsername(String numCalling,String number,String oldUsername, String newUsername);
    Operator findOperator(String numCalling,Operator operator);
    void logged(String numCalling,Operator operator);


}
