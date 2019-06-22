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
    private JTextField jOldIDTextField;
    private JTextField jNewIDTextField;
    private String number;
    private String numberCalling;

    public UpdateIDOperationGUI(String numberCalling,String number){
        this.number=number;
        this.numberCalling=numberCalling;
        initialize();}

     private void initialize(){
        this.setBounds(600,200,350,200);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("UpdateID operation window");
        JLabel jL1=new JLabel("Insert the old id of the operation: ");
        jL1.setBounds(10,10,300,25);
        this.add(jL1);
        jOldIDTextField =new JTextField("");
        jOldIDTextField.setBounds(10,40,150,25);
        this.add(jOldIDTextField);
        JLabel jL2=new JLabel("Insert the new id for the operation: ");
        jL2.setBounds(10,70,300,25);
        this.add(jL2);
        jNewIDTextField =new JTextField("");
        jNewIDTextField.setBounds(10,100,200,25);
        this.add(jNewIDTextField);
        JButton button=new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Socket socket = null;

                if(!jOldIDTextField.getText().equals("")&&!jNewIDTextField.getText().equals("")&&!jOldIDTextField.getText().trim().equals(jNewIDTextField.getText().trim())) {
                    if(isValid(jNewIDTextField.getText().trim())) {
                        try {
                            socket = new Socket(ServerInfo.IP, ServerInfo.PORT);
                            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                            ObjectInputStream is=new ObjectInputStream(socket.getInputStream());
                            os.writeObject(new MessageServer(MessageType.MODIFYIDOPERATION,numberCalling, jOldIDTextField.getText().trim(), number, jNewIDTextField.getText().trim()));
                            Operation operationUpdated=(Operation) is.readObject();
                            if(operationUpdated!=null&&operationUpdated.getId().equals(jOldIDTextField.getText().trim())){
                                end();
                            }
                        } catch (IOException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }


                    }
                    else{
                        IDIsANumber error=new IDIsANumber();
                        error.setVisible(true);
                    }
                }
                else{
                    EmptyField emptyError=new EmptyField();
                    emptyError.setVisible(true);
                }
            }
        });
        button.setBounds(125,135,75,20);
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

        for(int i=0;i<iDToCheck.length();i++){
            if(!Character.isDigit(iDToCheck.charAt(i))){
                return false;
            }
        }
        return true;
    }









}
