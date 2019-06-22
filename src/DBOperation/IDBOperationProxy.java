package DBOperation;

import model.Operation;

import java.util.ArrayList;

public interface IDBOperationProxy {


    void addOperation(String numCalling, Operation operation);
    void removeOperation(String numCalling,String id,String number);
    void updateID(String numCalling,String oldID, String number,String newID);
    void updateText(String numCalling,Operation operation);
    ArrayList<Operation> getAvailableOptionToShow(String numCalling,String numCall,String numberSequence);


}
