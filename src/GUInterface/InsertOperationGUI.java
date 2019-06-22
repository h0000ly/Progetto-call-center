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

    private JTextField jIDTextField;
    private JTextField jTextTextField;
    private String number;
    private String numCalling;

    public InsertOperationGUI(String numCalling,String number) {
        this.number = number;
        this.numCalling=numCalling;
        initialize();
    }

    /**
     * This method is used to add a new operation in the database operation
     */
    private void initialize(){
        this.setBounds(600,200,330,210);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("Add operation window");
        JLabel jL1=new JLabel("Insert the id of the operation: ");
        jL1.setBounds(10,10,300,25);
        this.add(jL1);
        jIDTextField =new JTextField("");
        jIDTextField.setBounds(10,40,150,25);
        this.add(jIDTextField);
        JLabel jL2=new JLabel("Insert the text for the operation: ");
        jL2.setBounds(10,70,300,25);
        this.add(jL2);
        jTextTextField =new JTextField("");
        jTextTextField.setBounds(10,100,200,25);
        this.add(jTextTextField);
        JButton button=new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Socket socket = null;
                    if(!jIDTextField.getText().equals("")&&!jTextTextField.getText().equals("")) {
                        if(isValid(jIDTextField.getText().trim())) {
                            try {
                                socket = new Socket(ServerInfo.IP, ServerInfo.PORT);
                                ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                                os.writeObject(new MessageServer(MessageType.ADDOPERATION,numCalling, new Operation(jIDTextField.getText().trim(), number, jTextTextField.getText().trim())));
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            end();
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
        button.setBounds(115,135,75,20);
        this.add(button);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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

    /**
     * This method dispose the window
     */
    private void end(){
        this.dispose();
    }

}
