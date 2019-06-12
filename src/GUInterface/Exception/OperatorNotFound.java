package GUInterface.Exception;


public class OperatorNotFound extends MasterClassGUIException{
    private static final String TOSHOW="Operator not found";
    private static final String TITLE="Error";
    public OperatorNotFound(){
        super(TITLE,TOSHOW);

    }
}
