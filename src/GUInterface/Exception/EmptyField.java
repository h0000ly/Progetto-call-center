package GUInterface.Exception;



public class EmptyField extends MasterClassGUIException{
    private static final String TOSHOW="All the fields must be filled";
    private static final String TITLE="Empty field";

    public EmptyField() {
        super(TITLE,TOSHOW);
    }
	
}
