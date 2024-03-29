package tester;

import ClientServer.MessageServer;
import ClientServer.MessageType;
import ClientServer.Server;
import DBOperator.DBOperatorDAO;
import model.Operator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TesterDBOperator {
    private String numCalling="222333";
    @Before
    public void DataLoad(){
        DBOperatorDAO instance=DBOperatorDAO.getInstance();
        instance.addOperatorToDatabase(numCalling,new Operator("5555","Nice Username","Nice Password"));
        instance.updateUsername(numCalling,"5555","Nice Username", "Nice Username 2");
        instance.updatePassword(numCalling,new Operator("5555","Nice Username 2","Nice Password 2"));

    }
    @After
    public void dataClean(){
        DBOperatorDAO instance=DBOperatorDAO.getInstance();
        instance.removeOperator("345556","5555","Nice Username 2");
    }


    @Test
    public void test() {
        Server server = new Server();
        MessageServer messageServer = new MessageServer(MessageType.JUSTTHEONEOPERATOR, "345556",new Operator("5555","Nice Username 2","Nice Password 2"));
        Operator result = server.findOperator(messageServer);
        assertThat(result.equals(new Operator("5555","Nice Username 2","Nice Password 2")), is(true));
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TesterDBOperator.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}


