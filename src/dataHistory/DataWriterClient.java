package dataHistory;

import java.io.IOException;

public class DataWriterClient extends DataWriter {
	
    private final static String HISTORY="src\\dataHistory\\historyClient.txt";
	
    public DataWriterClient(String numberCalling){
        super(HISTORY, numberCalling);
    }
	
    public synchronized void updateHistory(String toWrite) {
        try {
            super.updateHistory(toWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
