package dataHistory;

import java.io.IOException;

public class DataWriterServer extends DataWriter {
	
    private final static String HISTORY="src\\dataHistory\\historyServer.txt";
	
    public DataWriterServer(String numberCalling) {
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
