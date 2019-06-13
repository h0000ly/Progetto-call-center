package GUInterface;

import GUInterface.Exception.EmptyField;
import GUInterface.Exception.ErrorCall;
import GUInterface.Exception.NeedANumber;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberPhoneGUI extends JFrame {
    private JTextField jNumber;
    private JButton jButtonOk;

    public NumberPhoneGUI(){
        this.setBounds(600,300,350,170);
        this.setTitle("Number Phone Window");
        this.setResizable(false);
        this.setLayout(null);
        JLabel info=new JLabel("Please insert your phone number: ");
        info.setBounds(10,10,290,25);
        this.add(info);
        jNumber=new JTextField("");
        jNumber.setBounds(10,40,200,25);
        this.add(jNumber);
        jButtonOk =new JButton("OK");
        jButtonOk.setBounds(100,80,60,30);
        this.add(jButtonOk);
        jButtonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String toCheck=jNumber.getText().trim();
                if(!toCheck.equals("")){
                    if(isValid(toCheck)){
                        if(toCheck.length()<3||toCheck.length()>10){
                            ErrorCall errorCall=new ErrorCall();
                            errorCall.setVisible(true);
                        }
                        else {
                            CallGUI call = new CallGUI();
                            call.setVisible(true);
                            close();
                        }
                    }
                    else{
                        NeedANumber errorNumber=new NeedANumber();
                        errorNumber.setVisible(true);
                    }
                }
                else{
                    EmptyField error=new EmptyField();
                    error.setVisible(true);
                }
            }
        });



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
    private void close(){
        this.dispose();
    }
}
