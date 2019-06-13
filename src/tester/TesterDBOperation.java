package tester;


import ClientServer.MessageServer;
import ClientServer.MessageType;
import ClientServer.Server;
import DBOperation.DBOperationDAO;
import model.Operation;
import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.List;

public class TesterDBOperation {
@Before
public  void dataLoad(){
    DBOperationDAO instance=DBOperationDAO.getInstance();
    instance.addOperationToDatabase(new Operation("12","5569","Nice test"));
    instance.updateID("12","5569","27");
    instance.updateText("27","5569","Well done");
    //Creo oggetti operation
    //
}

@After
public void dataClean(){
    DBOperationDAO instance=DBOperationDAO.getInstance();
    instance.removeOperation("27","5569");
}

@Test
public void test() {
    Server server = new Server();
    MessageServer messageServer = new MessageServer(MessageType.RETURNAVAILABLECHOICES, "5569", "2");
    List<String> result = server.retrieveJustTheRight(messageServer);
    assertThat(result.isEmpty(), is(false));
}

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TesterDBOperation.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}

//assertEquals
//assertNull
//assertNotNull
//assertThat