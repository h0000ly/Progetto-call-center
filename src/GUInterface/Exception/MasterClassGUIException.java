package GUInterface.Exception;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public abstract class MasterClassGUIException extends JFrame {
	
    /**
     * macro for the allignment of the components
     */
    private final int WINDOWX = 600;
    private final int WINDOWY = 300;
    private final int WINDOWHEIGTH = 150;
    private final int BUTTONWIDTH = 60;
    private final int BUTTONHEIGTH = 35;
    private final int LABELX = 10;
    private final int LABELY = 10;
    private final int LABELHEIGHT = 50;
    private final int WINDOWWIDTH = 500;
	
	private final String BTNTEXT = "OK";

    public MasterClassGUIException(String windowTitle, String stringToShow) {
		// window setup
		this.setBounds(WINDOWX, WINDOWY, WINDOWWIDTH, WINDOWHEIGTH);
        this.setLayout(null);
        this.setTitle(windowTitle);
        this.setResizable(false);
		// labels setup
		JLabel text = new JLabel(stringToShow);
        text.setBounds(LABELX, LABELY, WINDOWWIDTH - LABELX, LABELHEIGHT);
        this.add(text);
		// button setup
		JButton button = new JButton(BTNTEXT);
        button.setBounds((WINDOWWIDTH - BUTTONWIDTH) / 2, LABELY + 40, BUTTONWIDTH, BUTTONHEIGTH);
        this.add(button);
        button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

    /**
     * This method is used to dispose the window
     */
    private void close() {
        this.dispose();
    }
	
}
