package GUInterface;

import ClientServer.MessageServer;
import ClientServer.MessageType;
import ClientServer.ServerInfo;
import GUInterface.Exception.EmptyField;
import GUInterface.Exception.ErrorRegisterLoginGUI;
import GUInterface.Exception.ExceptionEnum;
import GUInterface.Exception.PasswordNotEqual;
import model.Operator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UpdatePasswordOperatorGUI extends JFrame {
    private final String TITLE="UpdatePassword operator window";
    private final String PSW1LABEL="Insert your new password: ";
    private final String PSW2LABEL="Reinsert the new password: ";
    private final String BUTTON="OK";
    private final int WINDOWX1=600;
    private final int WINDOWY1=200;
    private final int WINDOWX2=400;
    private final int WINDOWY2=185;
    private final int PSW1LABELX1=10;
    private final int PSW1LABELY1=10;
    private final int PSW1LABELX2=200;
    private final int PSW1LABELY2=22;
    private final int PSW1TEXTX1=10;
    private final int PSW1TEXTY1=30;
    private final int PSW1TEXTX2=200;
    private final int PSW1TEXTY2=22;
    private final int PSW2LABELX1=10;
    private final int PSW2LABELY1=60;
    private final int PSW2LABELX2=200;
    private final int PSW2LABELY2=22;
    private final int PSW2TEXTX1=10;
    private final int PSW2TEXTY1=80;
    private final int PSW2TEXTX2=200;
    private final int PSW2TEXTY2=22;
    private final int BUTTONX1=150;
    private final int BUTTONY1=115;
    private final int BUTTONX2=75;
    private final int BUTTONY2=20;
    //private JTextField jT1;
    //private JTextField jT2;
    private String number;
    private String username;
    private String numCalling;
    private MenuOperationsGUI menuOperationsGUI;

    public UpdatePasswordOperatorGUI(MenuOperationsGUI menuOperationsGUI, String numCalling, String number, String username) {
        this.number = number;
        this.username = username;
        this.numCalling = numCalling;
        this.menuOperationsGUI = menuOperationsGUI;
        initialize();
    }
	
    private void initialize() {
        this.setBounds(WINDOWX1,WINDOWY1,WINDOWX2,WINDOWY2);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle(TITLE);
        JLabel jL1 = new JLabel(PSW1LABEL);
        jL1.setBounds(PSW1LABELX1,PSW1LABELY1,PSW1LABELX2,PSW1LABELY2);
        this.add(jL1);
        JTextField jT1 = new JTextField("");
        jT1.setBounds(PSW1TEXTX1,PSW1TEXTY1,PSW1TEXTX2,PSW1TEXTY2);
        this.add(jT1);
        JLabel jL2 = new JLabel(PSW2LABEL);
        jL2.setBounds(PSW2LABELX1,PSW2LABELY1,PSW2LABELX2,PSW2LABELY2);
        this.add(jL2);
        JTextField jT2 = new JTextField("");
        jT2.setBounds(PSW2TEXTX1,PSW2TEXTY1,PSW2TEXTX2,PSW2TEXTY2);
        this.add(jT2);
        JButton button = new JButton(BUTTON);
        button.setBounds(BUTTONX1,BUTTONY1,BUTTONX2,BUTTONY2);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Operator operatorUpdated = null;
                Socket socket = null;
                if (!jT1.getText().equals("") && !jT2.getText().equals("")) {
                    if (jT1.getText().trim().equals(jT2.getText().trim())) {
                        if(isValid(jT1)) {
                            try {
                                socket = new Socket(ServerInfo.IP, ServerInfo.PORT);
                                ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                                os.writeObject(new MessageServer(MessageType.MODIFYPASSWORD, numCalling, new Operator(number, username, jT1.getText().trim())));
                                operatorUpdated = (Operator) is.readObject();
                                menuOperationsGUI.setOperator(operatorUpdated);
                            } catch (IOException | ClassNotFoundException ex) {
                                ex.printStackTrace();
                            }
                            end();
                        }
                    }
                    else{
                        PasswordNotEqual error = new PasswordNotEqual();
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
     * This method is used to dispose the window
     */
    private void end(){
        this.dispose();
    }

    /**
     * This method is used to check if the input contains numbers, uppercase and lowercase character and not contains the space character
     * @param toCheck
     * @return
     */
    private boolean isValid(JTextField toCheck) {
        boolean isNumber = false;
        boolean isUpper = false;
        boolean isLower = false;
        boolean isShort = false;
        boolean isSpace = false;
        String toCompare = toCheck.getText().trim();
        ErrorRegisterLoginGUI errA;

        if (toCheck.getText().length() < 8) {
            isShort = true;
        }
        for (int i = 0; i < toCheck.getText().length(); i++) {
            if (Character.isUpperCase(toCompare.charAt(i))) {
                isUpper = true;
            }
            if (Character.isLowerCase(toCompare.charAt(i))) {
                isLower = true;
            }
            if (Character.isDigit(toCompare.charAt(i))) {
                isNumber = true;
            }
            if (Character.isSpaceChar(toCompare.charAt(i))) {
                isSpace = true;
            }
        }
        if (!isUpper) {
            errA = new ErrorRegisterLoginGUI(ExceptionEnum.UPPER);
            errA.setVisible(true);
        }
        if (!isNumber) {
            errA = new ErrorRegisterLoginGUI(ExceptionEnum.NUMBER);
            errA.setVisible(true);
        }
        if (!isLower) {
            errA = new ErrorRegisterLoginGUI(ExceptionEnum.LOWER);
            errA.setVisible(true);
        }
        if (isSpace) {
            errA = new ErrorRegisterLoginGUI(ExceptionEnum.SPACE);
            errA.setVisible(true);
        }
        if (isShort) {
            errA = new ErrorRegisterLoginGUI(ExceptionEnum.SHORT);
            errA.setVisible(true);
        }
        return (isNumber && isLower && !isSpace && !isShort && isUpper);
    }

}
