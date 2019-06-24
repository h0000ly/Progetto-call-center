package GUInterface;

import ClientServer.MessageServer;
import ClientServer.MessageType;
import ClientServer.ServerInfo;
import GUInterface.Exception.*;
import dataHistory.DataWriter;
import dataHistory.DataWriterClient;
import model.Operator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RegisterLogin extends JFrame {

    private final String LOGINPRESSED="The user pressed the login button";
    private final String REGISTERPRESSED="The user pressed the register button";
    private final String TITLE="Login/Register GUI";
    private final String USERLABELTEXT="Please insert Username: ";
    private final String PSWLABELTEXT="Please insert the password: ";
    private final String REGBUTTONTEXT="Register";
    private final String LOGBUTTONTEXT="Login";
    private final int WINDOWX1=600;
    private final int WINDOWY1=200;
    private final int WINDOWX2=350;
    private final int WINDOWY2=270;
    private final int USERLABELX1=10;
    private final int USERLABELY1=5;
    private final int USERLABELX2=250;
    private final int USERLABELY2=25;
    private final int USERTEXTX1=10;
    private final int USERTEXTY1=45;
    private final int USERTEXTX2=250;
    private final int USERTEXTY2=30;
    private final int PSWLABELX1=10;
    private final int PSWLABELY1=100;
    private final int PSWLABELX2=250;
    private final int PSWLABELY2=25;
    private final int PSWTEXTX1=10;
    private final int PSWTEXTY1=130;
    private final int PSWTEXTX2=250;
    private final int PSWTEXTY2=30;
    //private JTextField userText;
    //private JPasswordField pswText;
    //private JButton butLog;
    //private JButton butReg;
    private String numberCalled;
    private String numberCalling;
    private DataWriterClient dataWriter;

    public RegisterLogin(String numberCalling, String numberCalled) {
        dataWriter = new DataWriterClient(numberCalling);
        this.numberCalled = numberCalled;
        this.numberCalling = numberCalling;
        initComponents();
    }

    private void initComponents() {
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle(TITLE);
        this.setBounds(WINDOWX1,WINDOWY1,WINDOWX2,WINDOWY2);
        Container pane = this.getContentPane();
        pane.setLayout(null);
        JLabel userLabel = new JLabel(USERLABELTEXT);
        userLabel.setBounds(USERLABELX1,USERLABELY1,USERLABELX2,USERLABELY2);
        JTextField userText = new JTextField("");
        userText.setBounds(USERTEXTX1,USERTEXTY1,USERTEXTX2,USERTEXTY2);
        pane.add(userLabel);
        pane.add(userText);
        JLabel pswLabel = new JLabel(PSWLABELTEXT);
        pswLabel.setBounds(PSWLABELX1,PSWLABELY1,PSWLABELX2,PSWLABELY2);
        JPasswordField pswText = new JPasswordField();
        pswText.setBounds(PSWTEXTX1,PSWTEXTY1,PSWTEXTX2,PSWTEXTY2);
        pane.add(pswLabel);
        pane.add(pswText);
        JButton butLog = new JButton(LOGBUTTONTEXT);
        butLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Operator opOut = null;
                opOut = getExistingOperator(userText.getText().trim(),pswText.getText().trim());
                if (opOut != null && !opOut.isLoggedIn() && opOut.getPassword().equals(pswText.getText().trim())) {
                    dataWriter.updateHistory(LOGINPRESSED);
                    MenuOperationsGUI menu = new MenuOperationsGUI(numberCalling,opOut);
                    menu.setVisible(true);
                    close();
                }
                else{
                    if (opOut == null) {
                        OperatorNotFound oNF = new OperatorNotFound();
                        oNF.setVisible(true);
                    }
                    else if (opOut.isLoggedIn()) {
                        OperatorAlreadyLogged oAL=new OperatorAlreadyLogged();
                        oAL.setVisible(true);
                    }
                    else if (opOut != null) {
                        WrongPassword wo = new WrongPassword();
                        wo.setVisible(true);
                    }
                }
            }
        });
        butLog.setBounds(80,180,100,30);
        pane.add(butLog);
        JButton butReg = new JButton(REGBUTTONTEXT);
        butReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isValid(userText,pswText)) {
                    Socket socket = null;
                    Operator operator = null;
                    Operator operatorIn = getExistingOperator(userText.getText().trim(),pswText.getText().trim());
                    if (operatorIn == null) {
                        try {
                            socket = new Socket(ServerInfo.IP, ServerInfo.PORT);
                            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                            os.writeObject(new MessageServer(MessageType.ADDOPERATOR, numberCalling, new Operator(numberCalled, userText.getText().trim(), pswText.getText().trim())));
                            operator = (Operator) is.readObject();
                            if (operator != null && !operator.isLoggedIn()) {
                                dataWriter.updateHistory(REGISTERPRESSED);
                                MenuOperationsGUI menu = new MenuOperationsGUI(numberCalling,operator);
                                menu.setVisible(true);
                                close();
                            }
                        } catch (IOException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                    else {
                       // if(operatorIn.getNumber().equals(numberCalled) && operatorIn.getUsername().equals(userText.getText().trim())) {
                        OperatorAlreadyUsed oAU = new OperatorAlreadyUsed();
                        oAU.setVisible(true);
                       // }
                        //
                        // if/else have same body
                        //else {
                        // OperatorAlreadyUsed oAU = new OperatorAlreadyUsed();
                        // oAU.setVisible(true);
                        // }
                    }
                }
            }
        });
        butReg.setBounds(butLog.getX() + butLog.getWidth(), butLog.getY(), butLog.getWidth(), butLog.getHeight());
        pane.add(butReg);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }


    /**
     * This method is used to find a defined operator in input
     * @return
     */

    private Operator getExistingOperator(String username,String password) {
        Operator existingOperator = null;
        Socket socket = null;
        try {
            socket = new Socket(ServerInfo.IP, ServerInfo.PORT);
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            os.writeObject(new MessageServer(MessageType.JUSTTHEONEOPERATOR, numberCalling, new Operator(numberCalled, username, password)));
            existingOperator = (Operator) is.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return existingOperator;
    }

    /**
     * This method is used to check if the input contains numbers, uppercase and lowercase character and not contains the space character
     * @param userText
     * @param pswText
     * @return
     */
    private boolean isValid(JTextField userText, JPasswordField pswText){
        boolean isNumber=false;
        boolean isUpper=false;
        boolean isLower=false;
        boolean isShort=false;
        boolean isSpace=false;
        ErrorRegisterLoginGUI errA;
		char password[] = pswText.getPassword();

         if(password.length<8||userText.getText().trim().length()<8){
             isShort=true;
         }
         for(int i=0;i<password.length;i++){
             if (Character.isUpperCase(password[i])){
                 isUpper=true;
             }
             if(Character.isLowerCase(password[i])){
                 isLower=true;
             }
             if (Character.isDigit(password[i])){
                 isNumber=true;
             }
             if(Character.isSpaceChar(password[i])){
                 isSpace=true;
             }
         }
         if(!isUpper){
             errA=new ErrorRegisterLoginGUI(ExceptionEnum.UPPER);
             errA.setVisible(true);
         }
         if(!isNumber){
             errA=new ErrorRegisterLoginGUI(ExceptionEnum.NUMBER);
             errA.setVisible(true);
         }
         if(!isLower){
             errA=new ErrorRegisterLoginGUI(ExceptionEnum.LOWER);
             errA.setVisible(true);
         }
         if(isSpace){
             errA=new ErrorRegisterLoginGUI(ExceptionEnum.SPACE);
             errA.setVisible(true);
         }
         if(isShort){
             errA=new ErrorRegisterLoginGUI(ExceptionEnum.SHORT);
             errA.setVisible(true);
         }

		return (isNumber && isLower && !isSpace && !isShort && isUpper);
    }

    private void close(){
        this.dispose();
    }

}

