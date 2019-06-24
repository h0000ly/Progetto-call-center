package GUInterface.Exception;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorRegisterLoginGUI extends JFrame implements ActionListener {
	
	private final int WINDOWX = 600;
	private final int WINDOWY = 300;
	private final int SIZEX = 400;
	private final int SIZEY = 150;
	private final String TITLE = "Error";
	private final String BTNTEXT = "OK";
	private final int BTNBOUNDSX1 = 50;
	private final int BTNBOUNDSY1 = 10;
	private final int BTNBOUNDSX2 = 60;
	private final int BTNBOUNDSY2 = 30;
	
    private ExceptionEnum exceptionEnum;
    private String forTheAllign="       ";
	
    public ErrorRegisterLoginGUI(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
        initComponents();
    }
	
    private void initComponents(){
        this.setLocation(WINDOWX, WINDOWY);
        this.setVisible(true);
        this.setTitle(TITLE);
        this.setSize(SIZEX, SIZEY);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(2,1));
		// add label
		JLabel errLabel = new JLabel(forTheAllign + exceptionEnum.getValue());
        this.add(errLabel);
		// add button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        JButton OKButton = new JButton(BTNTEXT);
        OKButton.setBounds(BTNBOUNDSX1, BTNBOUNDSY1, BTNBOUNDSX2, BTNBOUNDSY2);
        OKButton.addActionListener(this);
        buttonPanel.add(OKButton);
        this.add(buttonPanel);
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