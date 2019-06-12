package GUInterface;

import ClientServer.MessageServer;
import ClientServer.MessageType;
import ClientServer.ServerInfo;
import GUInterface.Exception.EmptyField;
import GUInterface.Exception.ErrorRegisterLoginGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class UpdateUsernameOperatorGUI extends JFrame {
    private JTextField jT1;
    private String number;

    public UpdateUsernameOperatorGUI(String number,String username) {
        this.number = number;
        initialize(username);
    }

    private void initialize(String username){
        this.setBounds(600,200,370,150);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("Update username operator window");
        JLabel jL1=new JLabel("Insert your new Username: ");
        jL1.setBounds(10,10,300,25);
        this.add(jL1);
        jT1=new JTextField("");
        jT1.setBounds(10,35,200,25);
        this.add(jT1);

        JButton button=new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Socket socket = null;
                if(!jT1.getText().equals("")) {
                    if(isValid(jT1)){
                    try {
                        socket = new Socket(ServerInfo.IP, ServerInfo.PORT);
                        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                        os.writeObject(new MessageServer(MessageType.MODIFYUSERNAME, number, username, jT1.getText().trim()));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    end();
                    }
                    else{
                        ErrorRegisterLoginGUI error=new ErrorRegisterLoginGUI(1);
                        error.setVisible(true);
                    }
                }
                else{
                    EmptyField emptyField=new EmptyField();
                    emptyField.setVisible(true);
                }
            }
        });
        button.setBounds(135,80,60,20);
        this.add(button);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void end(){
        this.dispose();
    }

    private boolean isValid(JTextField toCheck){
        if(toCheck.getText().trim().length()<8){
            return false;
        }
        return true;
    }





}
