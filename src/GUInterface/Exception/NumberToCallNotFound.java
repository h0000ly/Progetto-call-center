package GUInterface.Exception;



public class NumberToCallNotFound extends MasterClassGUIException {
    private static final String TOSHOW="This numbers doesn't exist yet";
    private static final String TITLE="Number not found";
    public NumberToCallNotFound(){
        super(TITLE,TOSHOW);
    }
}
