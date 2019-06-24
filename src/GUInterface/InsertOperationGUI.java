package GUInterface;

import ClientServer.MessageServer;
import ClientServer.MessageType;
import ClientServer.ServerInfo;
import GUInterface.Exception.EmptyField;
import GUInterface.Exception.IDIsANumber;
import model.Operation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class InsertOperationGUI extends JFrame {
	
	private final String TITLE = "Add operation window";
	private final String MESSAGEID = "Insert the id of the operation: ";
	private final String MESSAGETEXT = "Insert the text for the operation: ";
	private final String BUTTON = "OK";
	private final int WINDOWX1 = 600;
	private final int WINDOWY1 = 200;
	private final int WINDOWX2 = 330;
	private final int WINDOWY2 = 210;
	private final int IDLABELX1 = 10;
	private final int IDLABELY1 = 10;
	private final int IDLABELX2 = 300;
	private final int IDLABELY2 = 25;
	private final int IDFIELDX1 = 10;
	private final int IDFIELDY1 = 40;
	private final int IDFIELDX2 = 150;
	private final int IDFIELDY2 = 25;
	private final int TEXTLABELX1 = 10;
	private final int TEXTLABELY1 = 70;
	private final int TEXTLABELX2 = 300;
	private final int TEXTLABELY2 = 25;
	private final int TEXTFIELDX1 = 10;
	private final int TEXTFIELDY1 = 100;
	private final int TEXTFIELDX2 = 200;
	private final int TEXTFIELDY2 = 25;
	private final int BUTTONX1 = 115;
	private final int BUTTONY1 = 135;
	private final int BUTTONX2 = 75;
	private final int BUTTONY2 = 20;
	
    private String number;
    private String numCalling;

    public InsertOperationGUI(String numCalling, String number) {
        this.number = number;
        this.numCalling=numCalling;
        initialize();
    }

    /**
     * This method is used to add a new operation in the database operation
     */
    private void initialize(){
        this.setBounds(WINDOWX1, WINDOWY1, WINDOWX2, WINDOWY2);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle(TITLE);
        JLabel idLabel=new JLabel(MESSAGEID);
        idLabel.setBounds(IDLABELX1, IDLABELY1, IDLABELX2, IDLABELY2);
        this.add(idLabel);
        JTextField  idField = new JTextField("");
        idField.setBounds(IDFIELDX1, IDFIELDY1, IDFIELDX2, IDFIELDY2);
        this.add(idField);
        JLabel textLabel=new JLabel(MESSAGETEXT);
        textLabel.setBounds(TEXTLABELX1, TEXTLABELY1, TEXTLABELX2, TEXTLABELY2);
        this.add(textLabel);
        JTextField textField = new JTextField("");
        textField.setBounds(TEXTFIELDX1, TEXTFIELDY1, TEXTFIELDX2, TEXTFIELDY2);
        this.add(textField);
        JButton button=new JButton(BUTTON);
        button.setBounds(BUTTONX1, BUTTONY1, BUTTONX2, BUTTONY2);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				Socket socket = null;
				if (!idField.getText().equals("") && !textField.getText().equals("")) {
					if (isValid(idField.getText().trim())) {
						try {
							socket = new Socket(ServerInfo.IP, ServerInfo.PORT);
							ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
							os.writeObject(new MessageServer(MessageType.ADDOPERATION,numCalling, new Operation(idField.getText().trim(), number, textField.getText().trim())));
						} catch (IOException ex) {
							ex.printStackTrace();
						}
						end();
					}
					else{
						IDIsANumber error = new IDIsANumber();
						error.setVisible(true);
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
     * This method is used to verify that the input is a number
     * @param iDToCheck
     * @return
     */
    private boolean isValid(String iDToCheck){
		return iDToCheck.chars().allMatch(x -> Character.isDigit(x));
    }

    /**
     * This method dispose the window
     */
    private void end(){
        this.dispose();
    }

}
