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

public class RegisterLogin extends JFrame implements ActionListener {
	
    private static final String LOGINPRESSED="The user pressed the login button";
    private static final String REGISTERPRESSED="The user pressed the register button";
	
    private JTextField userText;
    private JPasswordField pswText;
    private JButton butLog;
    private JButton butReg;
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
        this.setTitle("Login/Register GUI");
        this.setBounds(600,200,350,270);
        Container pane = this.getContentPane();
        pane.setLayout(null);
        JLabel userLabel = new JLabel("Please insert Username: ");
        userLabel.setBounds(10,5,250,25);
        userText = new JTextField("");
        userText.setBounds(userLabel.getX(),45,250,30);
        pane.add(userLabel);
        pane.add(userText);
        JLabel pswLabel = new JLabel("Please insert Password: ");
        pswLabel.setBounds(userLabel.getX(),100,250,25);
        pswText = new JPasswordField();
        pswText.setBounds(userLabel.getX(),130,250,30);
        pane.add(pswLabel);
        pane.add(pswText);
        butLog = new JButton("Login");
        butLog.addActionListener(this);
        butLog.setBounds(80,180,100,30);
        pane.add(butLog);
        butReg = new JButton("Register");
        butReg.addActionListener(this);
        butReg.setBounds(butLog.getX() + butLog.getWidth(), butLog.getY(), butLog.getWidth(), butLog.getHeight());
        pane.add(butReg);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("Login")) {
            Operator opOut = null;
            opOut = findTheOne();
			if (opOut != null && !opOut.isLoggedIn() && opOut.getPassword().equals(pswText.getText().trim())) {
				dataWriter.updateHistory(LOGINPRESSED);
				MenuOperationsGUI menu = new MenuOperationsGUI(numberCalling,opOut);
				menu.setVisible(true);
				this.dispose();
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
        if (e.getActionCommand().equalsIgnoreCase("Register")) {
            if (isValid(userText,pswText)) {
                Socket socket = null;
                Operator operator = null;
                Operator operatorIn = getExistingOperator();
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
                            this.dispose();
                        }
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                else {
                    if(operatorIn.getNumber().equals(numberCalled) && operatorIn.getUsername().equals(userText.getText().trim())) {
                        OperatorAlreadyUsed oAU = new OperatorAlreadyUsed();
                        oAU.setVisible(true);
                    }
					// TODO: fix
					// if/else have same body
                    else {
                    OperatorAlreadyUsed oAU = new OperatorAlreadyUsed();
                    oAU.setVisible(true);
                    }
                }
            }
        }
    }

    /**
     * This method is used to find a defined operator in input
     * @return
     */
	 // TODO: Add parameters to method in order to increase readibility
	 // ie: private Operator getExistingOperator(String username, String password) {...}
    private Operator getExistingOperator() {
        Operator existingOperator = null;
        Socket socket = null;
        try {
            socket = new Socket(ServerInfo.IP, ServerInfo.PORT);
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            os.writeObject(new MessageServer(MessageType.JUSTTHEONEOPERATOR, numberCalling, new Operator(numberCalled, userText.getText().trim(), pswText.getText().trim())));
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
		// TODO: I took the liberty to rearrange the code here, it should work still
		// if it doesn't, uncomment the original
		// --- original code:
        // if(c.length<8||userText.getText().trim().length()<8){
        //     isShort=true;
        // }
        // for(int i=0;i<c.length;i++){
        //     if (Character.isUpperCase(c[i])){
        //         isUpper=true;
        //     }
        //     if(Character.isLowerCase(c[i])){
        //         isLower=true;
        //     }
        //     if (Character.isDigit(c[i])){
        //         isNumber=true;
        //     }
        //     if(Character.isSpaceChar(c[i])){
        //         isSpace=true;
        //     }
        // }
        // if(!isUpper){
        //     errA=new ErrorRegisterLoginGUI(ExceptionEnum.UPPER);
        //     errA.setVisible(true);
        // }
        // if(!isNumber){
        //     errA=new ErrorRegisterLoginGUI(ExceptionEnum.NUMBER);
        //     errA.setVisible(true);
        // }
        // if(!isLower){
        //     errA=new ErrorRegisterLoginGUI(ExceptionEnum.LOWER);
        //     errA.setVisible(true);
        // }
        // if(isSpace){
        //     errA=new ErrorRegisterLoginGUI(ExceptionEnum.SPACE);
        //     errA.setVisible(true);
        // }
        // if(isShort){
        //     errA=new ErrorRegisterLoginGUI(ExceptionEnum.SHORT);
        //     errA.setVisible(true);
        // }
        // if(isNumber&&isLower&&!isSpace&&!isShort&&isUpper){
        //     return true;
        // }
        // return false;
		// --- new version:
		String username = userText.getText().trim();
		isShort = userText.length() < 8 || password.length() < 8;
		for (char c : password) {
			if (Character.isUpperCase(c)){
                isUpper = true;
            }
            if(Character.isLowerCase(c)){
                isLower = true;
            }
            if (Character.isDigit(c)){
                isNumber = true;
            }
            if(Character.isSpaceChar(c)){
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

