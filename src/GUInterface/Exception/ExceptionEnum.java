package GUInterface.Exception;

public enum ExceptionEnum {
	
    SHORT("Minimum length of 8 is required"),
    NUMBER("The password must contain a number"),
    UPPER("The password must contain an uppercase letter"),
    LOWER("The password must contain a lowercase letter"),
    SPACE("The space character is not allowed");

    private String labelToShow;
	
    public String getValue(){
        return labelToShow;
    }
    private ExceptionEnum(String labelToShow){
        this.labelToShow = labelToShow;
    }
	
}
