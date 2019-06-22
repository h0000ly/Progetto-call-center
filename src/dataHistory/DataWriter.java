package dataHistory;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class DataWriter {
    private BufferedWriter writeLine;
    private String numCalling;

    public DataWriter(String history,String numCalling){
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

    /**
     * This method is called to print a define message in the history storage
     * @param toWrite
     * @throws IOException
     */
    protected synchronized void updateHistory(String toWrite) throws IOException {
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
