package GUInterface;

import ClientServer.MessageServer;
import ClientServer.MessageType;
import ClientServer.ServerInfo;
import Domain.Operator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MenuOperationsGUI extends JFrame {
    private Operator operator;
    public MenuOperationsGUI(Operator operator){
        this.operator=operator;
        updateStatus();
    this.setBounds(600,200,400,300);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("Menu operations window");
        JButton jB1=new JButton("Delete Operation");
        jB1.setBounds(10,10,170,40);
        jB1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteOperationGUI DONGUI=new DeleteOperationGUI(operator.getNumber());
                DONGUI.setVisible(true);
            }
        });
        this.add(jB1);

        JButton jB2=new JButton("Add Operation");
        jB2.setBounds(200,10,170,40);
        jB2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               InsertOperationGUI IONGUI =new InsertOperationGUI(operator.getNumber());
               IONGUI.setVisible(true);
            }
        });
        this.add(jB2);

        JButton jB3=new JButton("Change ID operation");
        jB3.setBounds(10,60,170,40);
        jB3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateIDOperationGUI UIDONGUI= new UpdateIDOperationGUI(operator.getNumber());
                UIDONGUI.setVisible(true);
            }
        });
        this.add(jB3);

        JButton jB4=new JButton("Change Text operation");
        jB4.setBounds(200,60,170,40);
        jB4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateTextOperationGUI UTONGUI=new UpdateTextOperationGUI(operator.getNumber());
                UTONGUI.setVisible(true);
            }
        });
        this.add(jB4);


        JButton jB5=new JButton("Delete your account");
        jB5.setBounds(10,110,170,40);
        jB5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteOperatorGUI DORGUI=new DeleteOperatorGUI(getFrame(),operator);
                DORGUI.setVisible(true);
            }
        });
        this.add(jB5);


        JButton jB6=new JButton("Change your username");
        jB6.setBounds(200,110,170,40);
        jB6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateUsernameOperatorGUI UUORGUI= new UpdateUsernameOperatorGUI(operator.getNumber(),operator.getUsername());
                UUORGUI.setVisible(true);
            }
        });
        this.add(jB6);

        JButton jB7=new JButton("Change your password");
        jB7.setBounds(10,160,170,40);
        jB7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdatePasswordOperatorGUI UPORGUI=new UpdatePasswordOperatorGUI(operator.getNumber(),operator.getUsername());
                UPORGUI.setVisible(true);
            }
        });
        this.add(jB7);



        JButton jB8=new JButton("Logout");
        jB8.setBounds(200,160,170,40);
        jB8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStatus();
                close();
            }
        });
        this.add(jB8);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                updateStatus();
                close();
                CallGUI newCall=new CallGUI();
                newCall.setVisible(true);
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

    }
    public void updateStatus(){
        Socket socket = null;
        this.operator.setLoggedIn(!this.operator.isLoggedIn());
        try {
            socket = new Socket(ServerInfo.IP, ServerInfo.PORT);
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            os.writeObject(new MessageServer(MessageType.LOGGED,operator.getNumber(),operator.getUsername(),operator.isLoggedIn() ));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    private void close(){
        this.dispose();
    }

    private MenuOperationsGUI getFrame(){
        return this;
    }


}
