package GUInterface.Exception;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorRegisterLoginGUI extends JFrame implements ActionListener {
    private int n;
    private JLabel errLabel;
    private JPanel buttonPanel;
    private String forTheAllign="       ";
    public ErrorRegisterLoginGUI(int n){
        this.n=n;
        initComponents();
    }
    private void initComponents(){
        this.setLocation(600,300);
        this.setVisible(true);
        buttonPanel=new JPanel();
        buttonPanel.setLayout(null);

        this.setSize(400,150);
        this.setLayout(new GridLayout(2,1));
        switch(n){

            case 1:
                errLabel=new JLabel(forTheAllign+"Minimum length of 8 is required");
                break;

            case 2:
                errLabel=new JLabel(forTheAllign+"The password must contain an uppercase letter");
                break;
            case 3:
                errLabel=new JLabel(forTheAllign+"The password must contain a lowercase letter");
                break;
            case 4:
                errLabel=new JLabel(forTheAllign+"The password must contain a number");
                break;
            case 5:
                errLabel=new JLabel(forTheAllign+"The space character is not allowed");
            default:
                break;
        }

        this.add(errLabel);
        JButton OKButton=new JButton("OK");
        OKButton.addActionListener(this);
        buttonPanel.add(OKButton);
        OKButton.setBounds(50,10,60,30);
        this.add(buttonPanel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}