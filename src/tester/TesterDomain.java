package tester;

import Domain.Operator;

public class TesterDomain {
    public static void main(String[] args) {

        Operator c=new Operator("11","22","33");
        System.out.println(c.equalsIDString("11","32"));
        System.out.println(c.equalsOperator(new Operator("11","2","")));

        //Operation b=new Operation("11","22","33");
        //System.out.println(b.equalsIDString("11","22"));
        //System.out.println(b.equalsID(new Operation("11","22","43")));

    }
}
