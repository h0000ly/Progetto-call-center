package GUInterface.Exception;

public class NeedANumber extends MasterClassGUIException {
	
    private final static String TITLE = "Error Number Window";
    private final static String TEXT = "The field must be filled with a number";
	
    public NeedANumber(){
        super(TITLE,TEXT);
    }
	
}
