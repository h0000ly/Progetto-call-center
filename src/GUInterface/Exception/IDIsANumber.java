package GUInterface.Exception;



public class IDIsANumber extends MasterClassGUIException{
    private static final String TOSHOW="The id must be a number";
    private static final String TITLE="Error ID";
    public IDIsANumber(){
        super(TITLE,TOSHOW);

    }
}
