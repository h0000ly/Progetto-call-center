package GUInterface.Exception;

public class PasswordNotEqual extends MasterClassGUIException {
	
    private static final String TOSHOW = "The passwords are not the same";
    private static final String TITLE = "Error";
    
	public PasswordNotEqual() {
        super(TITLE,TOSHOW);
    }
	
}
