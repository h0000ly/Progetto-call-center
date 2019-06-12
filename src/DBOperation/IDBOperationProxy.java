package DBOperation;

import Domain.Operation;
import Domain.Operator;

import java.util.ArrayList;

public interface IDBOperationProxy {


    void addOperationToDatabase(Operation operation);
    void removeOperation(String id,String number);
    void updateID(String oldID, String number,String newID);
    void updateText(String id, String number, String newText);
    ArrayList<String> getAvailableOptionToShow(String numCall,String numberSequence);


}
