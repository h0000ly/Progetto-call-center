package ClientServer;

import model.Operator;

import java.util.ArrayList;

public interface IServerProxy {
        void addOperation(MessageServer messageServer);
        ArrayList<String> retrieveJustTheRight(MessageServer messageServer);
        Operator addAndRetrieveOperator(MessageServer messageServer);
        void changeUsername(MessageServer messageServer);
        void changePassword(MessageServer messageServer);
        void removeOperation(MessageServer messageServer);
        void changeID(MessageServer messageServer);
        void changeText(MessageServer messageServer);
        void removeOperator(MessageServer messageServer);
        Operator findOperator(MessageServer messageServer);
        void changeStatus(MessageServer messageServer);
}
