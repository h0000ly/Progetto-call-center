package GUInterface;

import ClientServer.MessageServer;
import ClientServer.MessageType;
import ClientServer.ServerInfo;
import GUInterface.Exception.EmptyField;
import GUInterface.Exception.ErrorRegisterLoginGUI;
import GUInterface.Exception.ExceptionEnum;
import GUInterface.Exception.PasswordNotEqual;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UpdatePasswordOperatorGUI extends JFrame {
    private JTextField jT1;
    private JTextField jT2;
    private String number;
    private String username;

    public UpdatePasswordOperatorGUI(String number,String username) {
        this.number = number;
        this.username=username;
        initialize();
    }
    private void initialize(){
        this.setBounds(600,200,400,185);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("UpdatePassword operator window");
        JLabel jL1=new JLabel("Insert your new password: ");
        jL1.setBounds(10,10,200,22);
        this.add(jL1);
        jT1=new JTextField("");
        jT1.setBounds(10,30,200,22);
        this.add(jT1);
        JLabel jL2=new JLabel("Reinsert the new password: ");
        jL2.setBounds(10,60,200,22);
        this.add(jL2);
        jT2=new JTextField("");
        jT2.setBounds(10,80,200,22);
        this.add(jT2);
        JButton button=new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Socket socket = null;
                if(!jT1.getText().equals("")&&!jT2.getText().equals("")) {
                    if(jT1.getText().trim().equals(jT2.getText().trim())) {
                        if(isValid(jT1)) {
                            try {
                                socket = new Socket(ServerInfo.IP, ServerInfo.PORT);
                                ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                                os.writeObject(new MessageServer(MessageType.MODIFYPASSWORD, number, username, jT2.getText().trim()));
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            end();
                        }
                    }
                    else{
                        PasswordNotEqual error=new PasswordNotEqual();
                        error.setVisible(true);
                    }
                }
                else{
                    EmptyField emptyError=new EmptyField();
                    emptyError.setVisible(true);
                }
            }
        });
        button.setBounds(150,115,75,20);
        this.add(button);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void end(){
        this.dispose();
    }

    private boolean isValid(JTextField toCheck){
        boolean isNumber=false;
        boolean isUpper=false;
        boolean isLower=false;
        boolean isShort=false;
        boolean isSpace=false;
        String toCompare=toCheck.getText().trim();
        ErrorRegisterLoginGUI errA;
        if(toCheck.getText().length()<8){
            isShort=true;
        }
        for(int i=0;i<toCheck.getText().length();i++){
            if (Character.isUpperCase(toCompare.charAt(i))){
                isUpper=true;
            }
            if(Character.isLowerCase(toCompare.charAt(i))){
                isLower=true;
            }
            if (Character.isDigit(toCompare.charAt(i))){
                isNumber=true;
            }
            if(Character.isSpaceChar(toCompare.charAt(i))){
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
        if(isNumber&&isLower&&!isSpace&&!isShort&&isUpper){
            return true;
        }
        return false;
    }
}
