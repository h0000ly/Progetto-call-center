package GUInterface.Exception;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorRegisterLoginGUI extends JFrame implements ActionListener {
    private ExceptionEnum exceptionEnum;
    private JLabel errLabel;
    private JPanel buttonPanel;
    private String forTheAllign="       ";
    public ErrorRegisterLoginGUI(ExceptionEnum exceptionEnum){
        this.exceptionEnum=exceptionEnum;
        initComponents();
    }
    private void initComponents(){
        this.setLocation(600,300);
        this.setVisible(true);
        this.setTitle("Error");
        buttonPanel=new JPanel();
        buttonPanel.setLayout(null);

        this.setSize(400,150);
        this.setLayout(new GridLayout(2,1));

        errLabel=new JLabel(forTheAllign+exceptionEnum.getValue());
        this.add(errLabel);
        JButton OKButton=new JButton("OK");
        OKButton.addActionListener(this);
        buttonPanel.add(OKButton);
        OKButton.setBounds(50,10,60,30);
        this.add(buttonPanel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * This method is used to close the window
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}