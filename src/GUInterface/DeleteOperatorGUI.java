package GUInterface;

import ClientServer.MessageServer;
import ClientServer.MessageType;
import ClientServer.ServerInfo;
import model.Operator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DeleteOperatorGUI extends JFrame {
    private Operator operator=null;
    private MenuOperationsGUI menuOperationsGUI=null;
    private String numCalling;
    public DeleteOperatorGUI(MenuOperationsGUI menuOperationsGUI,String numCalling,Operator operator) {
        this.operator = operator;
        this.menuOperationsGUI=menuOperationsGUI;
        this.numCalling=numCalling;
        initialize();
    }

    private void initialize(){
        this.setBounds(600,200,300,150);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("Delete operator window");
        JLabel jL1=new JLabel("Are you sure to delete this account ? ");
        jL1.setBounds(10,10,300,25);
        this.add(jL1);
        JButton button=new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Socket socket = null;
                try {
                    socket = new Socket(ServerInfo.IP, ServerInfo.PORT);
                    ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                    os.writeObject(new MessageServer(MessageType.DELETEOPERATOR,numCalling,operator.getNumber(),operator.getUsername()));

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                end();
                menuOperationsGUI.dispose();

            }
        });
        button.setBounds(50,60,75,30);
        this.add(button);
        JButton cancel=new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
            }
        });
        cancel.setBounds(130,60,75,30);
        this.add(cancel);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    private void end(){
        this.dispose();
    }
}
