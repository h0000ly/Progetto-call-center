package GUInterface;

import ClientServer.MessageServer;
import ClientServer.MessageType;
import ClientServer.ServerInfo;
import GUInterface.Exception.EmptyField;
import GUInterface.Exception.IDIsANumber;
import GUInterface.Exception.OperationAlreadyUsed;
import model.Operation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UpdateIDOperationGUI extends JFrame {

    private final String TITLE="UpdateID operation window";
    private final String OLDIDLABEL="Insert the old id of the operation: ";
    private final String NEWIDLABEL="Insert the new id for the operation: ";
    private final int WINDOWX1=600;
    private final int WINDOWY1=200;
    private final int WINDOWX2=350;
    private final int WINDOWY2=200;
    private final int OLDIDLABELX1=10;
    private final int OLDIDLABELY1=10;
    private final int OLDIDLABELX2=300;
    private final int OLDIDLABELY2=25;
    private final int OLDIDTEXTX1=10;
    private final int OLDIDTEXTY1=40;
    private final int OLDIDTEXTX2=150;
    private final int OLDIDTEXTY2=25;
    private final int NEWIDLABELX1=10;
    private final int NEWIDLABELY1=70;
    private final int NEWIDLABELX2=300;
    private final int NEWIDLABELY2=25;
    private final int NEWIDTEXTX1=10;
    private final int NEWIDTEXTY1=100;
    private final int NEWIDTEXTX2=200;
    private final int NEWIDTEXTY2=25;
    private final int BUTTONX1=125;
    private final int BUTTONY1=135;
    private final int BUTTONX2=75;
    private final int BUTTONY2=20;
    //private JTextField jOldIDTextField;
    //private JTextField jNewIDTextField;
    private String number;
    private String numberCalling;

    public UpdateIDOperationGUI(String numberCalling, String number) {
        this.number=number;
        this.numberCalling=numberCalling;
        initialize();
	}

     private void initialize() {
        this.setBounds(WINDOWX1,WINDOWY1,WINDOWX2,WINDOWY2);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle(TITLE);
        JLabel jL1 = new JLabel(OLDIDLABEL);
        jL1.setBounds(OLDIDLABELX1,OLDIDLABELY1,OLDIDLABELX2,OLDIDLABELY2);
        this.add(jL1);
        JTextField jOldIDTextField = new JTextField("");
        jOldIDTextField.setBounds(OLDIDTEXTX1,OLDIDTEXTY1,OLDIDTEXTX2,OLDIDTEXTY2);
        this.add(jOldIDTextField);
        JLabel jL2 = new JLabel(NEWIDLABEL);
        jL2.setBounds(NEWIDLABELX1,NEWIDLABELY1,NEWIDLABELX2,NEWIDLABELY2);
        this.add(jL2);
        JTextField jNewIDTextField = new JTextField("");
        jNewIDTextField.setBounds(NEWIDTEXTX1,NEWIDTEXTY1,NEWIDTEXTX2,NEWIDTEXTY2);
        this.add(jNewIDTextField);
        JButton button = new JButton("OK");
        button.setBounds(BUTTONX1,BUTTONY1,BUTTONX2,BUTTONY2);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Socket socket = null;
                if (!jOldIDTextField.getText().equals("") && !jNewIDTextField.getText().equals("") && !jOldIDTextField.getText().trim().equals(jNewIDTextField.getText().trim())) {
                    if (isValid(jNewIDTextField.getText().trim())) {
                        try {
                            socket = new Socket(ServerInfo.IP, ServerInfo.PORT);
                            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                            os.writeObject(new MessageServer(MessageType.MODIFYIDOPERATION, numberCalling, jOldIDTextField.getText().trim(), number, jNewIDTextField.getText().trim()));
                            Operation operationUpdated = (Operation) is.readObject();
                            if(operationUpdated != null && operationUpdated.getId().equals(jNewIDTextField.getText().trim())) {
                                end();
                            }
                        } catch (IOException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
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
     * This method is used to dispose the window
     */
    private void end(){
        this.dispose();
    }

    /**
     * This method is used to verify that the input is a number
     * @param iDToCheck
     * @return
     */
    private boolean isValid(String iDToCheck){
        return iDToCheck.chars().allMatch(x -> Character.isDigit(x));
    }









}
