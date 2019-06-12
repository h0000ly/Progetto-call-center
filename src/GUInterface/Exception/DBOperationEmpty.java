package GUInterface.Exception;


public class DBOperationEmpty  extends MasterClassGUIException {
    private static final String TOSHOW="The database of the operations is empty";
    private static final String TITLE="Database empty";

    public DBOperationEmpty() {
        super(TITLE, TOSHOW);
    }
}
