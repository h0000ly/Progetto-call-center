package DBOperator;


import model.Operator;

public interface IProxyDBOperator {

    void addOperatorToDatabase(String number,String username,String password);
    void removeOperator(String number,String username);
    void updatePassword(String number, String username,String newPassword);
    void updateUsername(String number,String oldUsername, String newUsername);
    Operator findOperator(Operator operator);
    void logged(String number,String username,boolean status);


}
