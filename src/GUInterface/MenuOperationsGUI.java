package GUInterface;

import ClientServer.MessageServer;
import ClientServer.MessageType;
import ClientServer.ServerInfo;
import dataWriterHistory.DataWriterClient;
import model.Operator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MenuOperationsGUI extends JFrame {
	
	private final String TITLE = "Menu operations window";
	private final int WINDOWX1 = 600;
	private final int WINDOWY1 = 200;
	private final int WINDOWX2 = 400;
	private final int WINDOWY2 = 300;
	
    private final String DELETEOPERATIONBUTTONPRESSED = "The operator pressed the Delete Operation button";
    private final String ADDOPERATIONBUTTONPRESSED = "The operator pressed the Delete Operation button";
    private final String CHANGEIDOPERATIONBUTTONPRESSED = "The operator pressed the Change ID Operation button";
    private final String CHANGETEXTOPERATIONBUTTONPRESSED = "The operator pressed the Change Text Operation button";
    private final String DELETEOPERATORBUTTONPRESSED = "The operator pressed the Delete Operator button";
    private final String CHANGEUSERNAMEOPERATORBUTTONPRESSED = "The operator pressed the Change Username button";
    private final String CHANGEPASSWORDOPERATORBUTTONPRESSED = "The operator pressed the Change Password button";
    private final String LOGOUTBUTTONPRESSED = "The operator pressed the Logout button";
	
    private Operator operator;
    private String numberCalling;
    private DataWriterClient dataWriter;
	
    public MenuOperationsGUI(String numberCalling, Operator operator){
        this.operator = operator;
        this.numberCalling = numberCalling;
        dataWriter = new DataWriterClient(numberCalling);
        updateStatus();
		
		this.setBounds(WINDOWX1, WINDOWY1, WINDOWX2, WINDOWY2);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle(TITLE);
		
        JButton delOpBtn = new JButton("Delete Operation");
        delOpBtn.setBounds(10,10,170,40);
        delOpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteOperationGUI deleteOperation = new DeleteOperationGUI(numberCalling, operator.getNumber());
                deleteOperation.setVisible(true);
                dataWriter.updateHistory(DELETEOPERATIONBUTTONPRESSED);
            }
        });
        this.add(delOpBtn);

        JButton addOpBtn = new JButton("Add Operation");
        addOpBtn.setBounds(200,10,170,40);
        addOpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               InsertOperationGUI insertOperation = new InsertOperationGUI(numberCalling,operator.getNumber());
               insertOperation.setVisible(true);
               dataWriter.updateHistory(ADDOPERATIONBUTTONPRESSED);
            }
        });
        this.add(addOpBtn);

        JButton changeIdOpBtn = new JButton("Change ID operation");
        changeIdOpBtn.setBounds(10,60,170,40);
        changeIdOpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateIDOperationGUI updateOperationId = new UpdateIDOperationGUI(numberCalling, operator.getNumber());
                updateOperationId.setVisible(true);
                dataWriter.updateHistory(CHANGEIDOPERATIONBUTTONPRESSED);
            }
        });
        this.add(changeIdOpBtn);

        JButton changeTextOpBtn = new JButton("Change Text operation");
        changeTextOpBtn.setBounds(200,60,170,40);
        changeTextOpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateTextOperationGUI updateOperationText = new UpdateTextOperationGUI(numberCalling, operator.getNumber());
                updateOperationText.setVisible(true);
                dataWriter.updateHistory(CHANGETEXTOPERATIONBUTTONPRESSED);
            }
        });
        this.add(changeTextOpBtn);


        JButton delAccntBtn = new JButton("Delete your account");
        delAccntBtn.setBounds(10,110,170,40);
        delAccntBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteOperatorGUI deleteOperator = new DeleteOperatorGUI(getFrame(),numberCalling, operator);
                deleteOperator.setVisible(true);
                dataWriter.updateHistory(DELETEOPERATORBUTTONPRESSED);
            }
        });
        this.add(delAccntBtn);


        JButton changeNameBtn = new JButton("Change your username");
        changeNameBtn.setBounds(200,110,170,40);
        changeNameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateUsernameOperatorGUI updateOperatorUsername= new UpdateUsernameOperatorGUI(getFrame(), numberCalling, operator.getNumber(), operator.getUsername());
                updateOperatorUsername.setVisible(true);
                dataWriter.updateHistory(CHANGEUSERNAMEOPERATORBUTTONPRESSED);
            }
        });
        this.add(changeNameBtn);

        JButton changePswdBtn = new JButton("Change your password");
        changePswdBtn.setBounds(10,160,170,40);
        changePswdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdatePasswordOperatorGUI updateOperatorPassword = new UpdatePasswordOperatorGUI(getFrame(), numberCalling, operator.getNumber(), operator.getUsername());
                updateOperatorPassword.setVisible(true);
                dataWriter.updateHistory(CHANGEPASSWORDOPERATORBUTTONPRESSED);
            }
        });
        this.add(changePswdBtn);



        JButton logOutBtn = new JButton("Logout");
        logOutBtn.setBounds(200,160,170,40);
        logOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataWriter.updateHistory(LOGOUTBUTTONPRESSED);
                close();
            }
        });
        this.add(logOutBtn);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) { }

            @Override
            public void windowClosing(WindowEvent e) { }

            @Override
            public void windowClosed(WindowEvent e) { 
				updateStatus();
            }

            @Override
            public void windowIconified(WindowEvent e) { }

            @Override
            public void windowDeiconified(WindowEvent e) { }

            @Override
            public void windowActivated(WindowEvent e) { }

            @Override
            public void windowDeactivated(WindowEvent e) { }
        });

    }

    /**
     * This method change the status (logged in or logged out) of a defined operator
     */
    public void updateStatus() {
        Socket socket = null;
        this.operator.setLoggedIn(!this.operator.isLoggedIn());
        try {
            socket = new Socket(ServerInfo.IP, ServerInfo.PORT);
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            os.writeObject(new MessageServer(MessageType.LOGGED, numberCalling, operator));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method dispose the window
     */
    private void close() {
        this.dispose();
    }

    public void updateOperator(Operator operator) {
        this.operator=operator;
    }

    private MenuOperationsGUI getFrame() {
        return this;
    }

}
