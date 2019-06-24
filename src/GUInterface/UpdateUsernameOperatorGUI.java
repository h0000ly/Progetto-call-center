package GUInterface;

import ClientServer.MessageServer;
import ClientServer.MessageType;
import ClientServer.ServerInfo;
import GUInterface.Exception.EmptyField;
import GUInterface.Exception.ErrorRegisterLoginGUI;
import GUInterface.Exception.ExceptionEnum;
import GUInterface.Exception.OperatorAlreadyUsed;
import model.Operator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UpdateUsernameOperatorGUI extends JFrame {

    private final int WINDOWX1=600;
    private final int WINDOWY1=200;
    private final int WINDOWX2=370;
    private final int WINDOWY2=150;
    private final int LABELUSERX1=10;
    private final int LABELUSERY1=10;
    private final int LABELUSERX2=300;
    private final int LABELUSERY2=25;
    private final int TEXTUSERX1=10;
    private final int TEXTUSERY1=35;
    private final int TEXTUSERX2=200;
    private final int TEXTUSERY2=25;
    private final int BUTTONX1=135;
    private final int BUTTONY1=80;
    private final int BUTTONX2=60;
    private final int BUTTONY2=20;
    private final String TITLE="Update username operator window";
    private final String LABEL="Insert your new Username: ";
    private final String BUTTON="OK";
    //private JTextField jT1;
    private String number;
    private String numCalling;
    private MenuOperationsGUI menuOperationsGUI;

    public UpdateUsernameOperatorGUI(MenuOperationsGUI menuOperationsGUI, String numCalling, String number, String username) {
        this.number = number;
        this.numCalling = numCalling;
        this.menuOperationsGUI = menuOperationsGUI;
        initialize(username);
    }

    private void initialize(String username) {
        this.setBounds(WINDOWX1,WINDOWY1,WINDOWX2,WINDOWY2);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle(TITLE);
        JLabel jL1 = new JLabel(LABEL);
        jL1.setBounds(LABELUSERX1,LABELUSERY1,LABELUSERX2,LABELUSERY2);
        this.add(jL1);
        JTextField jT1 = new JTextField("");
        jT1.setBounds(TEXTUSERX1,TEXTUSERY1,TEXTUSERX2,TEXTUSERY2);
        this.add(jT1);
        JButton button=new JButton(BUTTON);
        button.setBounds(BUTTONX1,BUTTONY1,BUTTONX2,BUTTONY2);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Operator updatedOperator = null;
                Socket socket = null;
                if (!jT1.getText().equals("")) {
                    if(isValid(jT1)){
						try {
							socket = new Socket(ServerInfo.IP, ServerInfo.PORT);
							ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
							ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
							os.writeObject(new MessageServer(MessageType.MODIFYUSERNAME, numCalling, number, username, jT1.getText().trim()));
							updatedOperator = (Operator) is.readObject();
							if (updatedOperator.getUsername().equals(username)){
								OperatorAlreadyUsed operatorAlreadyUsed = new OperatorAlreadyUsed();
								operatorAlreadyUsed.setVisible(true);
							}
							else {
								menuOperationsGUI.setOperator(updatedOperator);
								end();
							}
						} catch (IOException | ClassNotFoundException ex) {
							ex.printStackTrace();
						}
                    }
                    else {
                        ErrorRegisterLoginGUI error = new ErrorRegisterLoginGUI(ExceptionEnum.SHORT);
                        error.setVisible(true);
                    }
                }
                else{
                    EmptyField emptyField = new EmptyField();
                    emptyField.setVisible(true);
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
     * This method is used to check if the input is long at least 8 characters
     * @param toCheck
     * @return
     */
    private boolean isValid(JTextField toCheck){
        return toCheck.getText().trim().length() >= 8;
    }

}
