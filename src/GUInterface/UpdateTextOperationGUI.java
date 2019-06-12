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

public class UpdateTextOperationGUI extends JFrame{
    private JTextField jT1;
    private JTextField jT2;
    private String number;

    public UpdateTextOperationGUI(String number) {
        this.number = number;
        initialize();
    }
    private void initialize(){
        this.setBounds(600,200,345,180);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("UpdateText operation window");
        JLabel jL1=new JLabel("Insert the id of the operation to modify: ");
        jL1.setBounds(10,10,300,25);
        this.add(jL1);
        jT1=new JTextField("");
        jT1.setBounds(10,30,200,25);
        this.add(jT1);
        JLabel jL2=new JLabel("Insert the new text for the operation: ");
        jL2.setBounds(10,60,300,25);
        this.add(jL2);
        jT2=new JTextField("");
        jT2.setBounds(10,80,200,25);
        this.add(jT2);
        JButton button=new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Socket socket = null;
                if(!jT1.getText().equals("")&&!jT2.getText().equals("")) {
                    if(isValid(jT1.getText())) {
                        try {
                            socket = new Socket(ServerInfo.IP, ServerInfo.PORT);
                            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                            os.writeObject(new MessageServer(MessageType.MODIFYTEXTOPERATION, jT1.getText().trim(), number, jT2.getText().trim()));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        end();
                    }
                    else{
                        IDIsANumber errorID=new IDIsANumber();
                        errorID.setVisible(true);
                    }
                }
                else{
                    EmptyField emptyField=new EmptyField();
                    emptyField.setVisible(true);
                }
            }
        });
        button.setBounds(130,113,60,20);
        this.add(button);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private boolean isValid(String iDToCheck){

        for(int i=0;i<iDToCheck.length();i++){
            if(!Character.isDigit(iDToCheck.charAt(i))){
                return false;
            }
        }
        return true;
    }

    private void end(){
        this.dispose();
    }

}