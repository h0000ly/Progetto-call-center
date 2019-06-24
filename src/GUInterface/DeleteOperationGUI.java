package GUInterface;

import ClientServer.MessageServer;
import ClientServer.MessageType;
import ClientServer.ServerInfo;
import GUInterface.Exception.EmptyField;
import GUInterface.Exception.IDIsANumber;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.regex.Pattern;

public class DeleteOperationGUI extends JFrame {
	
	private final String TITLE = "Delete operation window";
	private final String INPUT = "Insert the id of the operation to delete: ";
	private final String BUTTON = "OK";
	private final int WINDOWX1 = 600;
	private final int WINDOWY1 = 200;
	private final int WINDOWX2 = 350;
	private final int WINDOWY2 = 150;
	private final int LABELX1 = 10;
	private final int LABELY1 = 10;
	private final int LABELX2 = 300;
	private final int LABELY2 = 25;
	private final int FIELDX1 = 10;
	private final int FIELDY1 = 40;
	private final int FIELDX2 = 150;
	private final int FIELDY2 = 25;
	private final int BUTTONX1 = 180;
	private final int BUTTONY1 = 40;
	private final int BUTTONX2 = 75;
	private final int BUTTONY2 = 20;
	
	
    private String number;
    private String numCalling;

    public DeleteOperationGUI(String numCalling, String number) {
        this.number = number;
        this.numCalling = numCalling;
        initiate();
    }


    private void initiate() {
        this.setBounds(WINDOWX1, WINDOWY1, WINDOWX2, WINDOWY2);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle(TITLE);
        JLabel inputLabel = new JLabel(INPUT);
        inputLabel.setBounds(LABELX1, LABELY1, LABELX2, LABELY2);
        this.add(inputLabel);
        JTextField inputField = new JTextField("");
        inputField.setBounds(FIELDX1, FIELDY1, FIELDX2, FIELDY2);
        this.add(inputField);
        JButton button = new JButton(BUTTON);
        button.setBounds(BUTTONX1, BUTTONY1, BUTTONX2, BUTTONY2);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!inputField.getText().equals("")) {
                    if (isValid(inputField.getText())) {
                        Socket socket = null;
                        try {
                            socket = new Socket(ServerInfo.IP, ServerInfo.PORT);
                            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                            os.writeObject(new MessageServer(MessageType.DELETEOPERATION, numCalling, inputField.getText().trim(), number));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        end();
                    }
                    else{
                        IDIsANumber errorID = new IDIsANumber();
                        errorID.setVisible(true);
                    }
                }
                else{
                    EmptyField emptyError = new EmptyField();
                    emptyError.setVisible(true);
                }
            }
        });
        this.add(button);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * This method dispose the window
     */
    private void end(){
        this.dispose();
    }

    /**
     * This method checks if the input is composed only by numbers
     * @param iDToCheck
     * @return
     */
    private boolean isValid(String iDToCheck) {
		return iDToCheck.chars().allMatch(x -> Character.isDigit(x));
    }
}
