package tester;


import DBOperator.DBOperatorDAO;

public class TesterDBOperator {
    public static void main(String[] args) {
        DBOperatorDAO.getInstance().addOperatorToDatabase("11","22","4564");
       // DBOperatorDAO.getInstance().removeOperator("11","7");
        //DBOperatorReader d=new DBOperatorReader();

        //DBOperatorDAO.getInstance().updateUsername("11","22","7");
        //DBOperatorDAO.getInstance().updatePassword("11","7","dwqgterwqtgrq");
    }
}
