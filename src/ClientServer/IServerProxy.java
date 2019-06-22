package ClientServer;

import model.Operation;
import model.Operator;

import java.util.ArrayList;

public interface IServerProxy {
        void addOperation(MessageServer messageServer);
        ArrayList<Operation> retrieveJustTheRight(MessageServer messageServer);
        Operator addAndRetrieveOperator(MessageServer messageServer);
        Operator changeUsername(MessageServer messageServer);
        Operator changePassword(MessageServer messageServer);
        void removeOperation(MessageServer messageServer);
        Operation changeID(MessageServer messageServer);
        void changeText(MessageServer messageServer);
        void removeOperator(MessageServer messageServer);
        Operator findOperator(MessageServer messageServer);
        void changeStatus(MessageServer messageServer);
}
