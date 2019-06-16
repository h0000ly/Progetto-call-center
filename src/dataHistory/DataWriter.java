package dataHistory;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataWriter {
    private final static String history="src\\dataHistory\\history.txt";
    private BufferedWriter writeLine;
    private String numCalling;

    public DataWriter(String numCalling){
        this.numCalling=numCalling;
        try{
            File file=new File(history);
            file.createNewFile();
            writeLine=new BufferedWriter(new FileWriter(history,true));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void updateHistory(String toWrite) throws IOException {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        writeLine.write(formatter.format(now.getTime()));
        writeLine.write("||");
        writeLine.write("# "+numCalling+" - ");
        writeLine.write(toWrite);
        writeLine.newLine();
        writeLine.flush();
    }
}
