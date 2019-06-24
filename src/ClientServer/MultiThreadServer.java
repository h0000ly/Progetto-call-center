package ClientServer;

import dataHistory.DataWriter;
import dataHistory.DataWriterServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

public class MultiThreadServer implements Runnable {
	
	// variables
    private Socket csocket;
    private Server real;
    private Map<MessageType, Method> methodCorrispondence = new HashMap<>();
	// messages
	private static final String STARTED = "SERVER ONLINE";
	private static final String CONNECTED = "Server connected";

    MultiThreadServer(Socket csocket) throws NoSuchMethodException, SocketException {
        this.csocket = csocket;
        csocket.setTcpNoDelay(true);

        real = new Server();

        methodCorrispondence.put(MessageType.ADDOPERATION, Server.class.getMethod("addOperation", MessageServer.class));
        methodCorrispondence.put(MessageType.RETURNAVAILABLECHOICES, Server.class.getMethod("retrieveJustTheRight",MessageServer.class));
        methodCorrispondence.put(MessageType.ADDOPERATOR, Server.class.getMethod("addAndRetrieveOperator",MessageServer.class));
        methodCorrispondence.put(MessageType.MODIFYUSERNAME, Server.class.getMethod("changeUsername",MessageServer.class));
        methodCorrispondence.put(MessageType.MODIFYPASSWORD, Server.class.getMethod("changePassword",MessageServer.class));
        methodCorrispondence.put(MessageType.DELETEOPERATION, Server.class.getMethod("removeOperation",MessageServer.class));
        methodCorrispondence.put(MessageType.MODIFYIDOPERATION, Server.class.getMethod("changeID",MessageServer.class));
        methodCorrispondence.put(MessageType.MODIFYTEXTOPERATION, Server.class.getMethod("changeText",MessageServer.class));
        methodCorrispondence.put(MessageType.DELETEOPERATOR, Server.class.getMethod("removeOperator",MessageServer.class));
        methodCorrispondence.put(MessageType.JUSTTHEONEOPERATOR, Server.class.getMethod("findOperator",MessageServer.class));
        methodCorrispondence.put(MessageType.LOGGED, Server.class.getMethod("changeStatus",MessageServer.class));
    }

    /**
     * Create the socket for the server and starts it
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        DataWriterServer data=new DataWriterServer("");
        ServerSocket ssock = new ServerSocket(ServerInfo.PORT);
        System.err.println(STARTED);
        data.updateHistory(STARTED);

        while (true) {
            Socket sock = ssock.accept();
            System.err.println(CONNECTED);

            new Thread(new MultiThreadServer(sock)).start();
        }
    }

    public void run() {
        try {
            ObjectInputStream inStream = new ObjectInputStream(csocket.getInputStream());
            ObjectOutputStream outStream = new ObjectOutputStream(csocket.getOutputStream());
            MessageServer message = (MessageServer) inStream.readObject();
            Object result = returnMessage(message.getMessageType(),message);
            outStream.writeObject(result);
            outStream.flush();
            csocket.close();
        } catch (IOException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Object returnMessage(MessageType tag, MessageServer messageServer) throws IllegalAccessException{
        Object result;
        try {
            result = methodCorrispondence.get(tag).invoke(real,messageServer);
        }
        catch (InvocationTargetException e){
                result = e.getCause();
        }
        return result;
    }
	
}
